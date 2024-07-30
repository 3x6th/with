package com.web3.with.security.oauth2.oauthuser;

import com.web3.with.security.oauth2.oauthuser.base.OAuth2UserInfo;
import java.util.Map;

/**
 * Class for getting user info from GitHub OAuth2.
 */
public class GitHubOAuth2UserInfo extends OAuth2UserInfo {

    public GitHubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("avatar_url");
    }

    @Override
    public String getLogin() {
        return (String) attributes.get("login");
    }

}
