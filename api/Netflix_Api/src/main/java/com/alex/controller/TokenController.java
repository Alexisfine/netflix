package com.alex.controller;

import com.alex.dto.TokenCreateDto;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/google")
    public Principal googleToken(Principal principal) {
        return principal;
    }
}
