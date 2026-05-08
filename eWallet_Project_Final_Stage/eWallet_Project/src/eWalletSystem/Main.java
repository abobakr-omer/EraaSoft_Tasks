package eWalletSystem;

import eWalletSystem.model.EWalletSystem;
import eWalletSystem.service.ValidationService;
import eWalletSystem.service.impl.EWalletApplicationServiceImpl;
import eWalletSystem.service.impl.ValidationServiceImpl;

public class Main {

    public static void main(String[] args) {

       new EWalletApplicationServiceImpl().start();



//        ValidationService validationService = new ValidationServiceImpl();
//
//        String phone = "+201554247788";
//
//        System.out.println(phone);
//        System.out.println(phone.length());
//        System.out.println(phone.matches("^\\+20(10|11|12|15)\\d{8}$"));
//        System.out.println(validationService.isPhoneNumberValid(phone));



    }


}
