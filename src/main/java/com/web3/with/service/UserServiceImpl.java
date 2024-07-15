package com.web3.with.service;

import com.web3.with.entity.UserEntity;
import com.web3.with.exception.http.NotFoundException;
import com.web3.with.mapper.UserMapper;
import com.web3.with.repository.UserRepository;
import com.web3.with.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserEntity findByUsername(String email) {
        return userRepository.findByEmailWithRoles(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.entityToUserPrincipal(findByUsername(username));
    }
}
