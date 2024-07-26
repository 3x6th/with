package com.web3.with.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailHelper {

    /**
     * Method for extracting login from email.
     *
     * @param email
     *         email of user.
     *
     * @return login
     */
    public static String parseEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        return email.substring(0, email.indexOf('@'));
    }

}
