package com.example.cardBin.repository;

import com.example.cardBin.model.Card;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CardRepository {

    @Value("${lastCheckedBIN.limit}")
    int limit;

    @Value("${lastCheckedBIN.positionToRemove}")
    int position = 0;

    @Getter
    @Setter
    private List<Card> cards = new ArrayList<>();

    @Getter
    private List<Card> lastCheckedBINs = new ArrayList<>();

    public Card findCard(int bin) {
        Card card = cards.stream()
                .filter(c -> c.getBin() == bin)
                .findFirst()
                .orElse(null);

        return card;
    }

    public void addCardToLocalData(Card card) {
        cards.add(card);
    }

    public void addCardToLastChecked(Card card) {
        while (lastCheckedBINs.size() >= limit) {
            lastCheckedBINs.remove(position);
        }

        lastCheckedBINs.add(card);
    }
}
