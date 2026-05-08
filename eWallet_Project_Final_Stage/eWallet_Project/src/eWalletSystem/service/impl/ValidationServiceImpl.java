package eWalletSystem.service.impl;

import eWalletSystem.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isUserNameValid(String userName) {
        if (userName==null||userName.isBlank()) return false;

        if (userName.length()<3) return false;

        // username must not contain number
        for (int i=0;i<userName.length();i++){
            if (Character.isDigit(userName.charAt(i))){
                return false;
            }
        }

        // username must start with char
        if (!Character.isLetter(userName.charAt(0))) return false;

        // username must start with upper
        if (!Character.isUpperCase(userName.charAt(0))) return false;

        return true;

    }

    @Override
    public boolean isPasswordValid(String password) {

        if (password==null||password.isBlank()) return false;

        if (password.length()<8) return false;

        // Password must start with a letter
        if (!Character.isLetter(password.charAt(0))) return false;



        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i=0;i<password.length();i++){

            // Password must not contain spaces
            if (Character.isWhitespace(password.charAt(i))) {
                return false;
            }

            if (Character.isUpperCase(password.charAt(i))){
                hasUpperCase=true;
            } else if (Character.isLowerCase(password.charAt(i))) {
                hasLowerCase=true;
            }else if (Character.isDigit(password.charAt(i))) {
                hasDigit=true;
            }else{
                hasSpecialChar=true;
            }

        }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;

    }

    @Override
    public boolean isAgeValid(Integer age) {
        if (age==null) return false;

        if (age<18) return false;


        return true;

    }

    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) return false;

        if (phoneNumber.length() != 13) return false;

        if (!phoneNumber.matches("^\\+20(10|11|12|15)\\d{8}$")) return false;

        for (int i = 1; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) return false;
        }

        return true;
    }

    // TODO Apply validation for all inputs


}
