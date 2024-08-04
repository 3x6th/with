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
import com.web3.with.util.EmailHelper;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.RoleName;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /**
     * Method for creating new user from OAuth2 user. Returns created user.
     *
     * @param oAuth2UserRequest
     *         request for loading user.
     * @param oAuth2UserInfo
     *         user info from OAuth2.
     *
     * @return {@link UserEntity} created user.
     */
    @Override
    @Transactional
    public UserEntity createOauth2User(
            OAuth2UserRequest oAuth2UserRequest,
            OAuth2UserInfo oAuth2UserInfo) {
        RoleEntity roleEntity = roleRepository.findByRole(RoleName.ROLE_DEFAULT); //TODO: исправить. Нужно получать из реквеста его

        String login = oAuth2UserInfo.getLogin()
                != null
                ? oAuth2UserInfo.getLogin()
                : EmailHelper.parseEmail(oAuth2UserInfo.getEmail());

        UserEntity userEntity = UserEntity.builder()
                                          .authProvider(
                                                  AuthProvider.valueOf(
                                                          oAuth2UserRequest.getClientRegistration()
                                                                           .getRegistrationId()
                                                  )
                                          )
                                          .email(oAuth2UserInfo.getEmail())
                                          .login(login)
                                          .role(roleEntity)
                                          .imageUrl(oAuth2UserInfo.getImageUrl())
                                          .build();
        return userRepository.save(userEntity);
    }

    /**
     * Method for finding user by email or login. Returns found user.
     *
     * @param identifier
     *         email or login.
     *
     * @return {@link UserEntity} found user.
     */
    @Override
    public UserEntity findByIdentifier(String identifier) {
        return userRepository.findByEmailOrLogin(identifier)
                             .orElseThrow(() -> new NotFoundException("User not found"));
    }

    /**
     * Method for checking if a user exists by email. Returns true if user exists, false otherwise.
     *
     * @param email email.
     * @return boolean true if user exists, false otherwise.
     */
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Method for saving user. Returns {@link UserEntity}.
     *
     * @param user
     *         user to save.
     *
     * @return {@link UserEntity} saved user.
     */
    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Long getUserIdFromCurrentSession() {
        //maybe bad solve
        var currentUserIdentifier = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return findByIdentifier(currentUserIdentifier).getId();
    }

    /**
     * Method for loading user by username. Returns {@link UserDetails}.
     *
     * @param username
     *         username.
     *
     * @return {@link UserDetails} user.
     *
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.entityToUserPrincipal(findByIdentifier(username));
    }

}
