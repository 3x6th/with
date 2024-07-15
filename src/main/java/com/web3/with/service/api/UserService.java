package com.web3.with.service.api;

import com.web3.with.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity findByUsername(String username);
    boolean existsByEmail(String username);
    void save(UserEntity user);
}
