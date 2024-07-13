package com.web3.with.service.api;

import com.web3.with.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    boolean existsByEmail(String username);
    void save(User user);
}
