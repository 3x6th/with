package com.web3.with.service;

import com.web3.with.entity.RoleEntity;
import com.web3.with.entity.UserEntity;
import com.web3.with.exception.http.NotFoundException;
import com.web3.with.mapper.UserMapper;
import com.web3.with.repository.RoleRepository;
import com.web3.with.repository.UserRepository;
import com.web3.with.security.model.auth.AuthProvider;
import com.web3.with.security.oauth2.oauthuser.base.OAuth2UserInfo;
import com.web3.with.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserEntity createOauth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = roleRepository.findByRole("ROLE_EMPLOYER");
        userEntity.setAuthProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        userEntity.setEmail(oAuth2UserInfo.getEmail());
        userEntity.setLogin(oAuth2UserInfo.getName());
        userEntity.setRole(roleEntity);
        userEntity.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(userEntity);
    }

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
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.entityToUserPrincipal(findByUsername(username));
    }
}
