package com.company.mapper;

import com.company.dto.AccountDTO;
import com.company.dto.CardDTO;
import com.company.model.Account;
import com.company.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    private MapperMediator mapperMediator;

    @Autowired
    public CardMapper(MapperMediator mapperMediator) {
        this.mapperMediator = mapperMediator;
    }

    public CardDTO toDTO(Card card){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardHolder(card.getCardHolder());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setExpirationDate(card.getExpirationDate());
        cardDTO.setId(card.getId());
        cardDTO.setStatus(card.getStatus());

        AccountDTO accountDTO = mapperMediator.getAccountMapper().toDTO(card.getAccount());
        cardDTO.setAccountDTO(accountDTO);
        return cardDTO;
    }

    public Card fromDTO(CardDTO cardDTO){
        Card card = new Card();
        card.setCardHolder(cardDTO.getCardHolder());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setExpirationDate(cardDTO.getExpirationDate());
        card.setId(cardDTO.getId());
        card.setStatus(cardDTO.getStatus());

        Account account = mapperMediator.getAccountMapper().fromDTO(cardDTO.getAccountDTO());
        card.setAccount(account);
        return card;
    }
}
