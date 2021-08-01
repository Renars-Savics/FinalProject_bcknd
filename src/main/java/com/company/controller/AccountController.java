package com.company.controller;

import com.company.dto.AccountDTO;

import com.company.dto.CardDTO;
import com.company.mapper.AccountMapper;
import com.company.mapper.CardMapper;
import com.company.mapper.MapperMediator;
import com.company.model.Account;
import com.company.model.Card;
import com.company.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

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
            List<Card> cards = account.getCards();
            List<CardDTO> cardDTOS = mapperMediator.getCardMapper().toDTOS(cards);
            tmp.setCardDTO(cardDTOS);
            accountDTOList.add(tmp);
        }
        return accountDTOList;
    }

    @GetMapping("/account({id})")
    public AccountDTO getAccountById(@PathVariable long id) {
        Account account = accountService.getAccountById(id);
        AccountDTO accountDTO = mapperMediator.getAccountMapper().toDTO(account);
        List<Card> cards = account.getCards();
        List<CardDTO> cardDTOS = mapperMediator.getCardMapper().toDTOS(cards);
        accountDTO.setCardDTO(cardDTOS);
        return accountDTO;
    }

    @PostMapping("/account")
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO) {
        Account account = mapperMediator.getAccountMapper().fromDTO(accountDTO);
        Account savedAccount = accountService.saveAccount(account);
        AccountDTO savedAccountDTO = mapperMediator.getAccountMapper().toDTO(savedAccount);
        return savedAccountDTO;
    }

    @GetMapping("/account/number/{accountNumber}")
    public List<AccountDTO> getAccountByNumberLike(@PathVariable String accountNumber) {
        List<Account> accountList = accountService.getAccountByNumberLike(accountNumber);
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountDTO tmp = mapperMediator.getAccountMapper().toDTO(account);
            List<Card> cards = account.getCards();
            List<CardDTO> cardDTOS = mapperMediator.getCardMapper().toDTOS(cards);
            tmp.setCardDTO(cardDTOS);
            accountDTOList.add(tmp);
        }
        return accountDTOList;
    }

}
