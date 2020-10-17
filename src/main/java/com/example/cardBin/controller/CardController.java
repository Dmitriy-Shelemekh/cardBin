package com.example.cardBin.controller;

import com.example.cardBin.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping(value = "/find/{bin}")
    public String findCard(@PathVariable("bin") int bin, Model model) {
        model.addAttribute("card", cardRepository.findCard(bin));
        model.addAttribute("lastChecked", cardRepository.getLastCheckedBINs());

        return "FindPage";
    }
}
