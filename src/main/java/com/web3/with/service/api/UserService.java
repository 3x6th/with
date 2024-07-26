package com.web3.with.service.api;

import com.web3.with.entity.UserEntity;
import com.web3.with.security.oauth2.oauthuser.base.OAuth2UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

/**
 * Service for user operations.
 */
public interface UserService extends UserDetailsService {

    UserEntity createOauth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo);

    UserEntity findByUsername(String username);

    boolean existsByEmail(String username);

    UserEntity save(UserEntity user);

}
