package com.example.bluebank.services;

import com.example.bluebank.model.Account;
import com.example.bluebank.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Iterable<Account> getAll(){
        return accountRepository.findAll();
    }

    public Optional<Account> getByNumber(String number){
        return accountRepository.findByNumber(number);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }
}
