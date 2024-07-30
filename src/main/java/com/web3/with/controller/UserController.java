package com.web3.with.controller;

import com.web3.with.security.model.context.UserContext;
import com.web3.with.security.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserContext userContext;

    /**
     * GET /user/me : Get information about the current user.
     *
     * @return {@link UserPrincipal} Information about the current user.
     */
    @GetMapping("/user/me")
    public UserPrincipal getCurrentUser() {
        return userContext.getCurrentUser();
    }

}
