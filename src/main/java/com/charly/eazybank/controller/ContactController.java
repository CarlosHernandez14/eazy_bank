package com.charly.eazybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @GetMapping
    public String saveContactInquiryDetails() {
        return "Contact Inquiry details saved in the DB";
    }


}
