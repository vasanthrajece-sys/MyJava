package com.vasanth.okta.resource;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/api/message")
    public String message(Jwt jwt) {
        return "Resource service accepted JWT for subject: " + jwt.getSubject();
    }
}
