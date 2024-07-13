package com.web3.with.controller;

import com.web3.with.entity.User;
import com.web3.with.exception.http.NotFoundException;
import com.web3.with.repository.UserRepository;
import com.web3.with.security.model.context.CurrentUser;
import com.web3.with.security.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findByEmailWithRoles(userPrincipal.getEmail())
                .orElseThrow(() -> new NotFoundException("User Not found " + userPrincipal.getEmail()));
    }
}
