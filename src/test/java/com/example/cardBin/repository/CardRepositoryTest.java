package com.example.cardBin.repository;

import com.example.cardBin.model.Card;
import com.example.cardBin.service.BinlistClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CardRepositoryTest {
    int binNumberForExistedCards = 45717360;
    int binNumberForBinListCards = 45717361;

    Card binListCard = new Card();

    Card existedCard = new Card();
    List<Card> existedCards = new ArrayList<>();

    @Autowired
    CardRepository repository;

    @Autowired
    BinlistClient client;

    @Test
    void findCardInExistListTest() {
        existedCard.setBin(binNumberForExistedCards);
        existedCards.add(existedCard);

        Assert.assertEquals(repository.findCard(binNumberForExistedCards).getBin(), existedCard.getBin());
    }

    @Test
    void findCardInBinListNetTest() {
        binListCard.setBin(binNumberForBinListCards);
        Mockito.when(client.findCard(any())).thenReturn(binListCard);

        Assert.assertEquals(repository.findCard(binNumberForBinListCards).getBin(), binListCard.getBin());
    }
}