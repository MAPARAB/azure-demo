package com.example.azure.controller;


import com.example.azure.model.Message;
import com.example.azure.pojo.ApiKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/web", produces="application/json")
public class GreetingController implements Greeting
{
    private static final Logger logger
            = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private Message message;


    @GetMapping("/greetings")
    public Message message()
    {
        logger.info("Inside the method message of class Greeting Controller");
        message.setMessage("Hurray ! The Spring boot web application is deployed and it worked");
        return message;
    }
}
