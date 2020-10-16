package com.example.cardBin.controller;

import com.example.cardBin.model.Card;
import com.example.cardBin.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/card")
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public String getCards(Model model) {
        List<Card> lastChecked = cardRepository.getLastCheckedBINs();
        model.addAttribute("lastChecked", lastChecked);

        log.info("Last 10 BIN`s was: {}", lastChecked);

        return "FindPage";
    }

    @GetMapping(value = "/find/")
    public String findCard(@RequestParam("bin") int bin, Model model) {
        Card card = cardRepository.findCard(bin);
        model.addAttribute("card", card);

        return "CardPage";
    }
}
