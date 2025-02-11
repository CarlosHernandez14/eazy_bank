package com.charly.eazybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notices")
public class NoticesController {

    @GetMapping
    public String getNotices() {
        return "Here are the notices details from the DB";
    }

}
