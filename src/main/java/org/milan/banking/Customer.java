package org.milan.banking;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents owner of one or more bank accounts
 *
 * @author Milan Rathod
 */
public class Customer {

    private List<BankAccount> accounts;

    private String panNumber;

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}
