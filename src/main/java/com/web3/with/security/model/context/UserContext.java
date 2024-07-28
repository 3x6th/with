package com.web3.with.security.model.context;

import com.web3.with.entity.UserEntity;
import com.web3.with.mapper.UserMapper;
import com.web3.with.security.principal.UserPrincipal;
import com.web3.with.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserContext {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserPrincipal getCurrentUser() {
        var currentUserIdentifier = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var entity = userService.findByIdentifier(currentUserIdentifier);
        return userMapper.entityToUserPrincipal(entity);
    }
}
