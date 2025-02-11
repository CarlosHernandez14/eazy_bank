package com.charly.eazybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myAccount")
public class AccountController {

    @GetMapping
    public String getAccount() {
        return "Here are the account details from the DB";
    }

}
