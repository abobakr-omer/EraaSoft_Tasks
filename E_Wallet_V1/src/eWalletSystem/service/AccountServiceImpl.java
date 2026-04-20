package eWalletSystem.service;

import eWalletSystem.exception.AuthenticationException;
import eWalletSystem.exception.DuplicateAccountException;
import eWalletSystem.model.Account;
import eWalletSystem.model.EWalletSystem;

public class AccountServiceImpl implements AccountService {

    private EWalletSystem eWalletSystem=new EWalletSystem();


    // Private helper method for searching by username
    private Account findAccountByUserName(String userName) {
        return eWalletSystem.getAccount().stream()
                .filter(account -> account.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    // Private helper method for searching by username and password
    private Account findAccountByUserNameAndPassword(String userName, String password) {
        return eWalletSystem.getAccount().stream()
                .filter(account -> account.getUserName().equals(userName)
                        && account.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean createAccount(Account account) {
        Account existingAccount = findAccountByUserName(account.getUserName());

        if (existingAccount != null) {
            return false;
        }

        eWalletSystem.getAccount().add(account);
        return true;
    }

    @Override
    public Boolean isAccountExistByUserNameAndPassword(Account account) {
        Account existingAccount = findAccountByUserNameAndPassword(
                account.getUserName(),
                account.getPassword()
        );

        return existingAccount != null;
    }


}
