package com.example.azure.controller;

import com.example.azure.model.Message;
import org.springframework.web.bind.annotation.RequestMapping;

public interface Greeting
{
    public Message message();
}
