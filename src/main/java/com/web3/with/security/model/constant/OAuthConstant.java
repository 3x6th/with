package com.web3.with.security.model.constant;

public enum OAuthConstant {
    EMAIL("email"), NAME("name"), PICTURE("picture"), LOGIN("login");

    private final String value;

    OAuthConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
