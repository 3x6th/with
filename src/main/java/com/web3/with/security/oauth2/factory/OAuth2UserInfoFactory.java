package com.web3.with.security.oauth2.factory;

import com.web3.with.exception.oauth2.OAuth2AuthenticationProcessingException;
import com.web3.with.security.oauth2.oauthuser.GitHubOAuth2UserInfo;
import com.web3.with.security.oauth2.oauthuser.GoogleOAuth2UserInfo;
import com.web3.with.security.oauth2.oauthuser.base.OAuth2UserInfo;
import java.util.Map;

/**
 * Factory for creating OAuth2UserInfo objects.
 */
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo createOAuth2UserInfo(
            String registrationId,
            final Map<String, Object> attributes) {
        return switch (registrationId.toLowerCase()) {
            case "google" -> new GoogleOAuth2UserInfo(attributes);
            case "github" -> new GitHubOAuth2UserInfo(attributes);
            default -> throw new OAuth2AuthenticationProcessingException(
                    "Sorry! Login with " + registrationId + " is not supported yet.");
        };
    }

}
