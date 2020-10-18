package com.example.cardBin.repository;

import com.example.cardBin.model.Card;
import com.example.cardBin.service.BinlistClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CardRepository {
    private static final int LIMIT = 10;
    private static final int FIRST = 0;
    private final BinlistClient binlistClient;

    @Getter
    @Setter
    private List<Card> cards = new ArrayList<>();

    @Getter
    private List<Card> lastCheckedBINs = new ArrayList<>();

    @Autowired
    public CardRepository(BinlistClient binlistClient) {
        this.binlistClient = binlistClient;
    }

    public Card findCard(int bin) {
        Card card = cards.stream()
                .filter(c -> c.getBin() == bin)
                .findFirst()
                .orElse(null);

        return card;
    }

    public void addCardChecked(Card card) {
        cards.add(card);
    }

    public void addCardToLastChecked(Card card) {
        while (lastCheckedBINs.size() >= LIMIT) {
            lastCheckedBINs.remove(FIRST);
        }

        lastCheckedBINs.add(card);
    }
}
