package com.company.controller;

import com.company.dto.AccountDTO;

import com.company.mapper.MapperMediator;
import com.company.model.Account;
import com.company.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rest/Account.svc")

public class AccountController {

    private AccountService accountService;


    private MapperMediator mapperMediator;

    @Autowired
    public AccountController(AccountService accountService, MapperMediator mapperMediator) {
        this.accountService = accountService;
        this.mapperMediator = mapperMediator;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> showAll() {
        List<Account> accountList = accountService.getAllAccounts();
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountDTO tmp = mapperMediator.getAccountMapper().toDTO(account);
            accountDTOList.add(tmp);
        }
        return accountDTOList;
    }


}
