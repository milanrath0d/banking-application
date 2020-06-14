package org.milan.banking;

/**
 * {@link BankAccount} of type Saving
 *
 * @author Milan Rathod
 */
public class SavingAccount extends BankAccount {

    public SavingAccount(String accountNumber, Double currentBalance) {
        this.minimumBalance = 10000.0;
        this.interestRate = 4.5;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
    }

    @Override
    public String getType() {
        return "Saving";
    }

    @Override
    public Double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public Double getInterestRate() {
        return interestRate;
    }
}
