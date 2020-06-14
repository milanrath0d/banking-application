package org.milan.banking;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class that defines a bank account
 *
 * @author Milan Rathod
 */
public abstract class BankAccount {

    protected String accountNumber;

    protected Double minimumBalance;

    protected Double currentBalance;

    protected Double interestRate;

    protected List<Transaction> transactions = new LinkedList<>();

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param amount amount to be withdraw
     */
    public synchronized void withdraw(Double amount) {
        if (currentBalance < amount || currentBalance - amount < minimumBalance) {
            throw new IllegalArgumentException("Withdraw: Balance is not sufficient");
        } else {
            currentBalance -= amount;
        }
        transactions.add(new Transaction(amount, "Withdraw"));
    }

    /**
     * @param amount amount to be deposit
     */
    public synchronized void deposit(Double amount) {
        if (amount == null || amount < 0.0) {
            throw new IllegalArgumentException("Deposit: Invalid value of amount entered");
        }
        currentBalance += amount;
        transactions.add(new Transaction(amount, "Deposit"));
    }

    /**
     * Get all transactions list
     */
    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    /**
     * Get last 10 performed transactions list
     */
    public List<Transaction> getMiniStatement() {
        return transactions.subList(Math.max(transactions.size() - 10, 0), transactions.size());
    }

    public abstract String getType();

    public abstract Double getMinimumBalance();

    public abstract Double getInterestRate();
}
