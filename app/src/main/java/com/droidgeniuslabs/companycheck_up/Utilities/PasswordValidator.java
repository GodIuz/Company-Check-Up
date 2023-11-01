package com.droidgeniuslabs.companycheck_up.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator
{
    public static boolean isPasswordValid(String password) {
        // Define regular expressions for capital letter, number, and special character.
        String capitalLetterRegex = ".*[A-Z].*";
        String numberRegex = ".*[0-9].*";
        String specialCharacterRegex = ".*[^A-Za-z0-9].*";

        // Combine the regex patterns into a single regex for password validation.
        String passwordRegex = capitalLetterRegex + numberRegex + specialCharacterRegex;

        // Create a pattern and matcher for the password.
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        // Check if all three criteria are met.
        return matcher.matches();
    }
}
