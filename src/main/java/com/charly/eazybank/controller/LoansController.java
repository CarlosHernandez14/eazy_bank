package com.charly.eazybank.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myLoans")
public class LoansController {

    @GetMapping
    public String getLoanDetails() {
        return "Here are the loan details from the DB";
    }

}
