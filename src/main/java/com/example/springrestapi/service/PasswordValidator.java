package com.example.springrestapi.service;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final String SPECIAL_CHARS_PATTERN = "[!@#$%^&*()_+\\-=\\[\\]{};':?,./~`]";
    private static final Pattern SPECIAL_CHARS = Pattern.compile(SPECIAL_CHARS_PATTERN);

    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }

        if (password.length() < MIN_LENGTH) {
            return false;
        }

        if (!SPECIAL_CHARS.matcher(password).find()) {
            return false;
        }

        return true;
    }

    public static String getValidationErrorMessage(String password) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty";
        }

        if (password.length() < MIN_LENGTH) {
            return "Password must be at least " + MIN_LENGTH + " characters long";
        }

        if (!SPECIAL_CHARS.matcher(password).find()) {
            return "Password must contain at least one special character";
        }

        return null;
    }
}
