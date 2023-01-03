package com.alex.controller;

import com.alex.dto.TokenCreateDto;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {
    private UserService userService;

    @Autowired
    public TokenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String createToken(@RequestBody TokenCreateDto tokenCreateDto) {
        return userService.createToken(tokenCreateDto);
    }
}
