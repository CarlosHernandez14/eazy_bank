package com.charly.eazybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myCards")
public class CardsController {

    @GetMapping
    public String getCardDetails() {
        return "Here are the card details from the DB";
    }

}
