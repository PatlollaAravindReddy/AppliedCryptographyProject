package com.example.demo.controller;


import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EncryptionDecryptionController {

    @RequestMapping(method = RequestMethod.GET, path = "/getdetails")
    public String getDetails() {
        return "aaaa";
    }
}
