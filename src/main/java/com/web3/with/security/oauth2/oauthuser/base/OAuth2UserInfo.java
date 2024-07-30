package com.web3.with.security.oauth2.oauthuser.base;

import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * Class for getting user info from OAuth2.
 */
@AllArgsConstructor
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public abstract String getLogin();
}
