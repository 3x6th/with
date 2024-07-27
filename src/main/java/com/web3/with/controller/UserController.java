package com.web3.with.controller;

import com.web3.with.security.model.context.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserContext currentUser;

    @GetMapping("/user/me")
    public String getCurrentUser() {
        return currentUser.getCurrentUser().getEmail();
    }
}
