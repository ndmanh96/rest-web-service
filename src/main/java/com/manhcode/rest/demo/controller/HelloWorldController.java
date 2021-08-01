package com.manhcode.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello")
    public String sayHello() {
        return messageSource.getMessage("good.morning.message", null,
                LocaleContextHolder.getLocale());
    }
}
