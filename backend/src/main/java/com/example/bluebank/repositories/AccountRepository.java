package com.example.bluebank.repositories;

import com.example.bluebank.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByNumber(String number);
}
