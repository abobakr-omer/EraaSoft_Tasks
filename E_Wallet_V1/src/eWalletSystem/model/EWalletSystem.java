package eWalletSystem.model;

import java.util.ArrayList;
import java.util.List;

public class EWalletSystem {

    private final String NAME="EraaSoft Wallet";

    private List<Account> accounts=new ArrayList<>();


    public List<Account> getAccount() {
        return accounts;
    }

    public void setAccount(Account account) {
        accounts.add(account);
    }



}
