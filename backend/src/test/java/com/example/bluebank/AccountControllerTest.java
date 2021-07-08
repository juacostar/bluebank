package com.example.bluebank;

import com.example.bluebank.model.Account;
import com.example.bluebank.requests.RequestAccount;
import com.example.bluebank.requests.RequestValue;
import com.example.bluebank.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BluebankApplication.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    private final Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();

    private final ObjectMapper objectMapper = mapperBuilder.build();

    Account createAccount(){
        Account account = new Account();
        account.setName("account_prueba");
        account.setNumber(account.generateNumber());
        account.setValue(1000.0);
        this.accountService.save(account);
        return account;
    }

    @Test
    void addNewAccount() throws Exception{
        String uri ="/account/add";
        RequestAccount requestAccount = new RequestAccount();
        requestAccount.setName("Account prueba");
        requestAccount.setValue(1200.0);
        this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestAccount))).andExpect(status().isOk());
//        List<Account> accounts = (List<Account>) this.accountService.getAll();
//        for(Account a: accounts){
//            this.accountService.deleteByNumber();
//        }
        this.accountService.deleteByName(requestAccount.getName());


    }

    @Test
    void consign() throws Exception{
        String uri = "/account/consign/";
        RequestValue requestValue = new RequestValue();
        requestValue.setValue(100.0);
        Account account = createAccount();
        Account saved = accountService.getByName(account.getName()).get(0);
        this.mockMvc.perform(put(uri + saved.getNumber())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestValue))).andExpect(status().isOk());
        this.accountService.deleteByName(account.getName());
    }

    @Test
    void retire() throws Exception{
        String uri = "/account/retire/";
        RequestValue requestValue = new RequestValue();
        requestValue.setValue(100.0);
        Account account = createAccount();
        Account saved = accountService.getByName(account.getName()).get(0);
        this.mockMvc.perform(put(uri + saved.getNumber())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestValue))).andExpect(status().isOk());
        this.accountService.deleteByName(account.getName());
    }

    @Test
    void getAccount() throws Exception{
        String uri = "/account/";
        Account account = createAccount();
        Account saved = accountService.getByName(account.getName()).get(0);
        ResultActions resultActions = this.mockMvc.perform(get(uri + saved.getNumber()))
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Account obtainedAccount = objectMapper.readValue(response, Account.class);
        assertEquals(obtainedAccount.getNumber(), saved.getNumber());
        this.accountService.deleteByName(account.getName());

    }


}
