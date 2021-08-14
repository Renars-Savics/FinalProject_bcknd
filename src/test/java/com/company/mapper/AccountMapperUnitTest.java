package com.company.mapper;

import com.company.dto.AccountDTO;
import com.company.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperUnitTest {

    private AccountMapper accountMapper = new AccountMapper();

    @Test
    public void toDTO() {

        Account account = new Account();
        account.setAccountNumber("001");
        account.setStatus(1);
        account.setCurrency("LVL");
        account.setBallance(1000);
        account.setId(56);

        AccountDTO accountDTO = accountMapper.toDTO(account);
        assertEquals("001", accountDTO.getAccountNumber());
        assertEquals(1, accountDTO.getStatus());
        assertEquals("LVL", accountDTO.getCurrency());
        assertEquals(1000, accountDTO.getBallance());
        assertEquals(56, accountDTO.getId());
    }

    @Test
    public void fromDTO() {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber("111");
        accountDTO.setStatus(2);
        accountDTO.setCurrency("USD");
        accountDTO.setBallance(999);
        accountDTO.setId(1);

        Account account = accountMapper.fromDTO(accountDTO);
        assertEquals("111", account.getAccountNumber());
        assertEquals(2, account.getStatus());
        assertEquals("USD", account.getCurrency());
        assertEquals(999, account.getBallance());
        assertEquals(1, account.getId());
    }
}