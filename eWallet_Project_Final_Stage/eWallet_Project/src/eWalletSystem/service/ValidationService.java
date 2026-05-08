package eWalletSystem.service;

public interface ValidationService {


    boolean isUserNameValid(String userName);

    boolean isPasswordValid(String password);

    boolean isAgeValid(Integer age);

    boolean isPhoneNumberValid(String phoneNumber);

}
