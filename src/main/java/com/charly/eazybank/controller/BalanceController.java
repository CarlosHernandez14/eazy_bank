package com.charly.eazybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myBalance")
public class BalanceController {

    @GetMapping
    public String getBalanceDetails() {
        return "Here is the balance details from the DB";
    }

}
