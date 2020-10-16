package com.example.cardBin.service;

import com.example.cardBin.model.Card;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
public class BinlistClient {

    @Value("${binlist.url}")
    private String url;

    private RestTemplate restTemplate = new RestTemplate();

    public Card checkBin(@NonNull int bin) {
        try {
            URI uri = new URI(url + bin);
            Card card = restTemplate.getForObject(uri, Card.class);
            card.setBin(bin);

            return card;
        } catch (URISyntaxException e) {
            log.error("Invalid Syntax for URL: {}, with BIN: {}", url, bin);
        }

        return null;
    }
}
