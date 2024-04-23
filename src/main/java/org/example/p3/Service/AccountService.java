package org.example.p3.Service;

import lombok.RequiredArgsConstructor;
import org.example.p3.Api.ApiException;
import org.example.p3.Model.Account;
import org.example.p3.Model.Customer;
import org.example.p3.Model.User;
import org.example.p3.Repository.AccountRepository;
import org.example.p3.Repository.AuthRepository;
import org.example.p3.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    public void CreateAccount(Integer userId ,Account account) {
        User u = authRepository.findUserById(userId);
        Customer c = customerRepository.findCustomerById(u.getId());

        account.setCustomer(c);
        accountRepository.save(account);


    }


    //View account details
    public Account accountDetail(Integer userId , Integer accountId) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);


        if (account.getCustomer().getId() == user.getId()) {
            return account;
        }

        return null;
    }



    public void deposit(Integer userId , Integer accountId, Integer amount) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);


        if (account.getCustomer().getId() == user.getId()) {
            account.setBalance(account.getBalance() + amount);

            accountRepository.save(account);
        }

    }


    public void withdraw(Integer userId , Integer accountId, Integer amount) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);

        if (account.getCustomer().getId() == user.getId()) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }
        }
    }



//Transfer funds between accounts
    public void transferFunds(Integer userAccountId ,  Integer anotherUserId ,  Integer amount) {

        Account account = accountRepository.findAccountById(userAccountId);
        Account anotherAccount = accountRepository.findAccountById(anotherUserId);

        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            anotherAccount.setBalance(anotherAccount.getBalance() + amount);
            accountRepository.save(account);
            accountRepository.save(anotherAccount);
        }


    }


}


