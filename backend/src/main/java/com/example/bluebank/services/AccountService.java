package com.example.bluebank.services;

import com.example.bluebank.model.Account;
import com.example.bluebank.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deleteByNumber(String number){
        this.accountRepository.deleteByNumber(number);
    }

    public void deleteByName(String name){
        this.accountRepository.deleteByName(name);
    }

    public List<Account> getByName(String name){
        return this.accountRepository.findByName(name);
    }
}
