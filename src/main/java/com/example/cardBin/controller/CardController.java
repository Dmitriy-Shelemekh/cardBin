package com.example.cardBin.controller;

import com.example.cardBin.model.Card;
import com.example.cardBin.repository.CardRepository;
import com.example.cardBin.service.FindService;
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

    private final CardRepository repository;
    private FindService service;

    @Autowired
    public CardController(CardRepository repository, FindService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public String getCards(Model model) {
        model.addAttribute("lastChecked", repository.getLastCheckedBINs());

        return "FindPage";
    }

    @GetMapping(value = "/find/")
    public String findCard(@RequestParam("bin") int bin, Model model) {
        Card card = service.find(bin);

        repository.addCardToLocalData(card);
        repository.addCardToLastChecked(card);

        model.addAttribute("card", card);
        model.addAttribute("lastChecked", repository.getLastCheckedBINs());

        return "FindPage";
    }
}
