package com.company.validator;

import com.company.exception.TooManyCardsException;
import com.company.model.Account;
import com.company.model.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardValidatorUnitTest {

    private CardValidator cardValidator = new CardValidator();

    @Test
    public void checkMaxCardAmount() {

        Account account = new Account();
        List<Card> cardList = new ArrayList<>();
        Card newCard1 = new Card();
        Card newCard2 = new Card();
        Card newCard3 = new Card();

        cardList.add(newCard1);
        cardList.add(newCard2);

        account.setCards(cardList);

        cardValidator.checkMaxCardAmount(account);

        cardList.add(newCard3);

        assertThrows(TooManyCardsException.class, () -> cardValidator.checkMaxCardAmount(account));
    }

}