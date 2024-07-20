package com.web3.with.service;

import com.web3.with.entity.UserEntity;
import com.web3.with.exception.oauth2.OAuth2AuthenticationProcessingException;
import com.web3.with.mapper.UserMapper;
import com.web3.with.repository.UserRepository;
import com.web3.with.security.model.auth.AuthProvider;
import com.web3.with.security.oauth2.factory.OAuth2UserInfoFactory;
import com.web3.with.security.oauth2.oauthuser.base.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOauth2ServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
        Optional<UserEntity> userOptional = userRepository.findByEmailWithRoles(oAuth2UserInfo.getEmail());
        UserEntity user;

        // Если пользователь существует, обновляем его данные
        if (userOptional.isPresent()) {
            user = userOptional.get();
            validateAuthProvider(user, oAuth2UserRequest);
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            // Если пользователя нет, создаем нового
            user = createOauth2User(oAuth2UserRequest, oAuth2UserInfo);
        }

        // Возвращаем объект UserPrincipal с атрибутами
        return userMapper.entityToUserPrincipal(user, oAuth2User.getAttributes());
    }

    private UserEntity createOauth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAuthProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        userEntity.setEmail(oAuth2UserInfo.getEmail());
        userEntity.setLogin(oAuth2UserInfo.getName());
        userEntity.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(userEntity);
    }

    private UserEntity updateExistingUser(UserEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setLogin(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
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
