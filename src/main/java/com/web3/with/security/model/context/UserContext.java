package com.web3.with.security.model.context;

import com.web3.with.entity.UserEntity;
import com.web3.with.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserContext {

    private final UserService userService;

    public UserEntity getCurrentUser() {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByIdentifier((String) currentUser);
    }
}
