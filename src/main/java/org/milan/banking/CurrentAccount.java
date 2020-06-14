package org.milan.banking;

/**
 * {@link BankAccount} of type Current
 *
 * @author Milan Rathod
 */
public class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountNumber, Double currentBalance) {
        this.minimumBalance = 20000.0;
        this.interestRate = 0.0;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
    }

    @Override
    public String getType() {
        return "Current";
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
