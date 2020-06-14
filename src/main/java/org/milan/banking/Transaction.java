package org.milan.banking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represent all transactions that are made against a bank account
 *
 * @author Milan Rathod
 */
public class Transaction {

    private String transactionId;

    private Double amount;

    private String type;

    private static AtomicInteger id = new AtomicInteger();

    public Transaction(Double amount, String type) {
        this.transactionId = String.valueOf(id.addAndGet(1));
        this.amount = amount;
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}
