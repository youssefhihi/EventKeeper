package com.EventKeeper.utility;

public class ValidateUser {

    public static boolean validateUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.length() >= 3;
    }
    public static boolean validatePassword(String password) {
        return password != null && !password.trim().isEmpty() && password.length() >= 4;
    }
}
