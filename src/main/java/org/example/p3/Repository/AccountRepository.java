package org.example.p3.Repository;

import org.example.p3.Model.Account;
import org.example.p3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findAccountById(Integer id);

}
