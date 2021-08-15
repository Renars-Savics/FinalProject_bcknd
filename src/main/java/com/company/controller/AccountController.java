package com.company.controller;

import com.company.dto.AccountDTO;

import com.company.dto.CardDTO;
import com.company.mapper.MapperMediator;
import com.company.model.Account;
import com.company.model.Card;
import com.company.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "/api/rest/Account.svc")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);   //Factory method for get logger

    private AccountService accountService;

    private MapperMediator mapperMediator;

    @Autowired
    public AccountController(AccountService accountService, MapperMediator mapperMediator) {
        this.accountService = accountService;
        this.mapperMediator = mapperMediator;
    }
    @CrossOrigin(origins = "http://localhost:8000")
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
    public AccountDTO addAccount(@Valid @RequestBody AccountDTO accountDTO) {
//        if(accountDTO.getAccountNumber().length() != 21){
//            throw new RuntimeException ("Account number has to be 21 characters long!");
//        }
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

    @PutMapping("/account/{accountId}/card/{cardId}/add")
    public void addCardToAccount(@PathVariable long accountId, @PathVariable long cardId) {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        accountService.addCardToAccount(accountId, cardId);
    }

    @PutMapping("account/remove/{cardId}")
    public void removeCardFromAccount(@PathVariable long cardId) {
        accountService.removeCardFromAccount(cardId);
    }

}
