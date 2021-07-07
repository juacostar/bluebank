package com.example.bluebank.controllers;

import com.example.bluebank.model.Account;
import com.example.bluebank.requests.RequestAccount;
import com.example.bluebank.requests.RequestValue;
import com.example.bluebank.services.AccountService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private static final String CU_CREADA = "Cuenta creada";
    private static final String CU_NO_ENCONTRADA = "Cuenta no encontrada";
    private static final String VAL_RET_INVALIDO = "Valor de retiro inválido";
    private static final String CON_ELABORADA = "Consignación elaborada";
    private static final String RET_ELABORADO = "Retiro elaborado";
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "account/add")
    public @ResponseBody ResponseEntity<Account> addNewAccount(@RequestBody RequestAccount account){
        Account a = new Account();
        a.setNumber(a.generateNumber());
        a.setName(account.getName());
        a.setValue(account.getValue());
        Account saved = this.accountService.save(a);
        LOGGER.info(CU_CREADA);
        return ResponseEntity.ok().body(saved);
    }

    @PutMapping(path = "account/consign/{number}")
    public ResponseEntity<Account> consign(@PathVariable String number, @RequestBody RequestValue value){
        Optional<Account> optionalAccount = this.accountService.getByNumber(number);
        if(optionalAccount.isEmpty()){
            LOGGER.info(CU_NO_ENCONTRADA);
            return ResponseEntity.badRequest().body(null);
        }
        Account account = optionalAccount.get();
        account.setValue(account.getValue() + value.getValue());
        Account saved = this.accountService.save(account);
        LOGGER.info(CON_ELABORADA);
        return ResponseEntity.ok().body(saved);

    }

    @PutMapping(path = "account/retire/{number}")
    public ResponseEntity<Account> retire(@PathVariable String number, @RequestBody RequestValue value){
        Optional<Account> optionalAccount = this.accountService.getByNumber(number);
        if(optionalAccount.isEmpty()){
            LOGGER.info(CU_NO_ENCONTRADA);
            return ResponseEntity.badRequest().body(null);
        }
        Account account = optionalAccount.get();
        if(account.getValue() > value.getValue())account.setValue(account.getValue() - value.getValue());
        else{
            LOGGER.info(VAL_RET_INVALIDO);
            return ResponseEntity.badRequest().body(null);
        }
        Account saved = this.accountService.save(account);
        LOGGER.info(RET_ELABORADO);
        return ResponseEntity.ok().body(saved);

    }

    @GetMapping(path = "account/{number}")
    public ResponseEntity<Account> getAccount(@PathVariable String number){
        Optional<Account> optionalAccount = this.accountService.getByNumber(number);
        if(optionalAccount.isEmpty()) {
            LOGGER.info(CU_NO_ENCONTRADA);
            return ResponseEntity.badRequest().body(null);
        }
        Account account = optionalAccount.get();
        return ResponseEntity.ok().body(account);
    }

}
