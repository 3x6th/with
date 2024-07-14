package com.web3.with.security.oauth2.factory;

import com.web3.with.exception.oauth2.OAuth2AuthenticationProcessingException;
import com.web3.with.security.oauth2.oauthuser.GitHubOauth2UserInfo;
import com.web3.with.security.oauth2.oauthuser.GoogleOauth2UserInfo;
import com.web3.with.security.oauth2.oauthuser.base.Oauth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public Oauth2UserInfo createOAuth2UserInfo(String registrationId, final Map<String, Object> attributes) {
        return switch (registrationId.toLowerCase()){
            case "google" -> new GoogleOauth2UserInfo(attributes);
            case "github" -> new GitHubOauth2UserInfo(attributes);
            default -> throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        };
    }
}
