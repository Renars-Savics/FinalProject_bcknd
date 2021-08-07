package com.company.controller;

import com.company.dto.AccountDTO;
import com.company.dto.CardDTO;
import com.company.mapper.MapperMediator;
import com.company.model.Account;
import com.company.model.Card;
import com.company.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rest/Card.svc")
public class CardController {

    private Logger logger = LoggerFactory.getLogger(CardController.class);   //Factory method for get logger

    private CardService cardService;

    private MapperMediator mapperMediator;


    @Autowired
    public CardController(CardService cardService, MapperMediator mapperMediator) {
        this.cardService = cardService;
        this.mapperMediator = mapperMediator;
    }

    @GetMapping("/cards")
    public List<CardDTO> showAll(){
        List<Card> cardList = cardService.getAllCards();
        List<CardDTO> cardDTOList = new ArrayList<>();
        for(Card card : cardList){
            CardDTO tmp = mapperMediator.getCardMapper().toDTO(card);
            cardDTOList.add(tmp);
        }
        return cardDTOList;
    }
    @PostMapping("/card")
    public CardDTO addCard(@RequestBody CardDTO cardDTO){
        Card card = mapperMediator.getCardMapper().fromDTO(cardDTO);
        Card saveCard = cardService.saveCard(card);
        CardDTO savedCardDTO = mapperMediator.getCardMapper().toDTO(saveCard);
        return savedCardDTO;
    }
    @GetMapping("/account/{id}")
    public List<CardDTO> getCardByAccountId(@PathVariable long id){
        List<CardDTO> cardDTOList = new ArrayList<>();
        List<Card> cardList = cardService.getAllByAccountId(id);
        for(Card card : cardList){
            CardDTO tmp = mapperMediator.getCardMapper().toDTO(card);
            cardDTOList.add(tmp);
        }
        return cardDTOList;
    }


}
