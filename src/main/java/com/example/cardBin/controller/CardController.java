package com.example.cardBin.controller;

import com.example.cardBin.model.Card;
import com.example.cardBin.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/card")
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public String getCards(Model model) {
        model.addAttribute("lastChecked", cardRepository.getLastCheckedBINs());

        return "FindPage";
    }

    @GetMapping(value = "/find/")
    public String findCard(@RequestParam("bin") int bin, Model model) {
        Card card = cardRepository.findCard(bin);

        cardRepository.addCardToLastChecked(card);

        model.addAttribute("card", card);
        model.addAttribute("lastChecked", cardRepository.getLastCheckedBINs());

        return "FindPage";
    }
}
