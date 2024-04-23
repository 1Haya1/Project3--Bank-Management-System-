package org.example.p3.Service;

import lombok.RequiredArgsConstructor;
import org.example.p3.DTO.CustomerDTO;
import org.example.p3.Model.Customer;
import org.example.p3.Model.User;
import org.example.p3.Repository.AuthRepository;
import org.example.p3.Repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;


    public void registerCustomer(User user , CustomerDTO customerDTO){

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setRole("CUSTOMER");

        String hash = new BCryptPasswordEncoder().encode(user1.getPassword());

        Customer customer = new Customer();
        customer.setId(customerDTO.getUser_id());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());


        user1.setPassword(hash);
        user1.setCustomer(customer);
        customer.setUser(user1);

        customerRepository.save(customer);
        authRepository.save(user1);
    }
}
