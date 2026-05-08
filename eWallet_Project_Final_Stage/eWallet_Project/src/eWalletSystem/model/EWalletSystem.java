package eWalletSystem.model;

import java.util.ArrayList;
import java.util.List;

public class EWalletSystem {

    private final String NAME="EraaSoft Wallet";

    private List<Account> accounts=new ArrayList<>();

    public String getName() {
        return NAME;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }



}
