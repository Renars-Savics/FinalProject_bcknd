package com.company.mapper;

import com.company.dto.CardDTO;
import com.company.model.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardMapper {


    public CardDTO toDTO(Card card){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardHolder(card.getCardHolder());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setExpirationDate(card.getExpirationDate());
        cardDTO.setId(card.getId());
        cardDTO.setStatus(card.getStatus());
        return cardDTO;
    }

    public Card fromDTO(CardDTO cardDTO){
        Card card = new Card();
        card.setCardHolder(cardDTO.getCardHolder());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setExpirationDate(cardDTO.getExpirationDate());
        card.setId(cardDTO.getId());
        card.setStatus(cardDTO.getStatus());
        return card;
    }
    public List<CardDTO> toDTOS(List<Card> cards){
        return cards.stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<Card> fromDTOS(List<CardDTO> cards){
        return cards.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
