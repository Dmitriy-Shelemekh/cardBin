package com.example.cardBin.service;

import com.example.cardBin.model.Card;
import com.example.cardBin.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FindService {

    private CardRepository repository;
    private BinlistClient client;

    @Autowired
    public FindService(CardRepository repository, BinlistClient client) {
        this.repository = repository;
        this.client = client;
    }

    public Card find(int bin) {
        Card card = repository.findCard(bin);

        if (card == null) {
            card = client.findCard(bin);
//            repository.addCardChecked(card);
//            cards.add(card);

            log.info("In binlist.net was found: {}", card);
        }

        repository.addCardChecked(card);

        return card;
    }
}
