package com.guojin.api.resource.server.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/print")
public class PrintMessageController {


    @GetMapping(value = "/message")
    public String get(@RequestParam String message) {
        System.out.println("message:" + message);
        return "this is resource api return:" + message;
    }

}
