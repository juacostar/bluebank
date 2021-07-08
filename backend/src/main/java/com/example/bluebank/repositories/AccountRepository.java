package com.example.bluebank.repositories;

import com.example.bluebank.model.Account;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByNumber(String number);
    void deleteByNumber(String number);
    @Transactional
    void deleteByName(String name);
    @Transactional
    List<Account> findByName(String name);
}
