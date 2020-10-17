package com.example.cardBin.repository;

import com.example.cardBin.model.Card;
import com.example.cardBin.service.BinlistClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CardRepository {
    private static final int LIMIT = 10;
    private final BinlistClient binlistClient;
    private List<Card> cards = new ArrayList<>();

    @Getter
    private List<Card> lastCheckedBINs = new ArrayList<>();

    {
        Card card = new Card();
        card.setBin(45717360);
        cards.add(card);
    }

    @Autowired
    public CardRepository(BinlistClient binlistClient) {
        this.binlistClient = binlistClient;
    }

    public Card findCard(int bin) {
        Card card = cards.stream()
                .filter(c -> c.getBin() == bin)
                .findFirst()
                .orElse(null);

        if (card == null) {
            card = binlistClient.checkBin(bin);
            cards.add(card);

            log.info("In binlist.net was found: {}", card);
        }

        while (lastCheckedBINs.size() >= LIMIT) {
            lastCheckedBINs.remove(0);
        }

        lastCheckedBINs.add(card);

        return card;
    }
}
