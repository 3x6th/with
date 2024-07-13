package com.web3.with.controller;

import com.web3.with.security.model.context.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/me")
    public String getCurrentUser(@CurrentUser String userPrincipal) {
        return userPrincipal;
    }
}
