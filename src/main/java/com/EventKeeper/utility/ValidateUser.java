package com.EventKeeper.utility;

public class ValidateUser {

    /**
     * Validates whether a given username is valid.
     *
     * @param username	the username to be validated
     * @return         	true if the username is valid, false otherwise
     */
    public static boolean validateUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.length() >= 3;
    }

    /**
     * Validates whether a given password is valid.
     *
     * @param  password  the password to be validated
     * @return            true if the password is valid, false otherwise
     */
    public static boolean validatePassword(String password) {
        return password != null && !password.trim().isEmpty() && password.length() >= 4;
    }
}
