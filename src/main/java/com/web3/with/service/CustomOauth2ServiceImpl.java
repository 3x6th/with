package com.web3.with.service;

import com.web3.with.entity.UserEntity;
import com.web3.with.exception.oauth2.OAuth2AuthenticationProcessingException;
import com.web3.with.mapper.UserMapper;
import com.web3.with.security.model.auth.AuthProvider;
import com.web3.with.security.oauth2.factory.OAuth2UserInfoFactory;
import com.web3.with.security.oauth2.oauthuser.base.OAuth2UserInfo;
import com.web3.with.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomOauth2ServiceImpl extends DefaultOAuth2UserService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }


    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        // Получаем информацию о пользователе из OAuth2
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.createOAuth2UserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );

        // Проверяем, что email присутствует
        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        // Ищем пользователя по email
        Optional<UserEntity> userOptional = Optional.ofNullable(userService.findByUsername(oAuth2UserInfo.getEmail()));
        UserEntity user;

        // Если пользователь существует, обновляем его данные
        if (userOptional.isPresent()) {
            user = userOptional.get();
            validateAuthProvider(user, oAuth2UserRequest);
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            // Если пользователя нет, создаем нового
            user = userService.createOauth2User(oAuth2UserRequest, oAuth2UserInfo);
        }

        // Возвращаем объект UserPrincipal с атрибутами
        return userMapper.entityToUserPrincipal(user, oAuth2User.getAttributes());
    }

    private UserEntity updateExistingUser(UserEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setLogin(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userService.save(existingUser);
    }

    private void validateAuthProvider(UserEntity user, OAuth2UserRequest oAuth2UserRequest) {
        AuthProvider currentAuthProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        if (!user.getAuthProvider().equals(currentAuthProvider)) {
            throw new OAuth2AuthenticationProcessingException(
                    "Looks like you're signed up with " + user.getAuthProvider() +
                            " account. Please use your " + user.getAuthProvider() + " account to login."
            );
        }
    }
}
