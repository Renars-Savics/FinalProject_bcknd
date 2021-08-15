package com.company.controller;


import com.company.dto.AccountDTO;
import com.company.model.Account;
import com.company.model.Card;
import com.company.repository.AccountRepository;
import com.company.repository.CardRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource(
        locations = "classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountControllerIntegrationTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;

    @Test
    public void getAccountById() throws Exception {
        MvcResult result = mvc.perform(get("/api/rest/Account.svc/account(1)")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        AccountDTO account = new ObjectMapper().readValue(content, AccountDTO.class);
        System.out.println(account);

        assertEquals("test001", account.getAccountNumber());
        assertEquals("USD", account.getCurrency());
        assertEquals(999, account.getBallance());
    }
    @Test
    public void getAccountByNumberLike() throws Exception {
        MvcResult result = mvc.perform(get("/api/rest/Account.svc/account/number/test001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<AccountDTO> accounts = new ObjectMapper().readValue(content, new TypeReference<List<AccountDTO>>() {});

        assertEquals("test001", accounts.get(0).getAccountNumber());
        assertEquals("USD", accounts.get(0).getCurrency());
        assertEquals(999, accounts.get(0).getBallance());
        assertEquals(2, accounts.get(0).getCardDTO().size());
    }

    @Test
    public void addAccount() throws Exception {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber("000000000000000000001");
        accountDTO.setCurrency("EUR");
        accountDTO.setBallance(888);
        accountDTO.setStatus(1);

        MvcResult result = mvc.perform(post("/api/rest/Account.svc/account")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(accountDTO)))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        AccountDTO account = new ObjectMapper().readValue(content, AccountDTO.class);

        assertNotNull(account.getId());

        long id = account.getId();
        Account tempAccount = accountRepository.getById(id);

        assertNotNull(tempAccount);
    }

    @Test
    @Transactional //jāpieliek, ja mums kodā ir Lazy Loading
    public void removeCardFromAccount() throws Exception {

        Card card = cardRepository.getById(1L);
        assertNotNull(card.getAccount());

        mvc.perform(put("/api/rest/Account.svc/account/remove/1"))
                .andExpect(status().isOk());

        card = cardRepository.getById(1L);
        assertNull(card.getAccount());
    }

    @Test
    @Transactional
    public void addCardToAccount() throws Exception {

        Account account = accountRepository.getById(1L);
        List<Card> cards = account.getCards();
        for(Card c : cards ){
            assertNotEquals(2, c.getId());
        }
        mvc.perform(put("/api/rest/Account.svc/account/1/card/2/add"))
                .andExpect(status().isOk());

        Card card = cardRepository.getById(2L);
        System.out.println(card);
        assertEquals(1, card.getAccount().getId());

    }
}