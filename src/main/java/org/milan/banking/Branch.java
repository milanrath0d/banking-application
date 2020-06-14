package org.milan.banking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Branch of a bank. It keeps track of all the customers and bank accounts that
 * are created in that specific bank account
 *
 * @author Milan Rathod
 */
public class Branch {

    private List<BankAccount> bankAccounts = new ArrayList<>();

    private List<Customer> customers = new ArrayList<>();

    private String branchId;

    private static AtomicInteger accountId = new AtomicInteger();

    public Branch(String branchId) {
        this.branchId = branchId;
    }

    /**
     * Get branchId of the bank
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * Create new bank account for a customer identified by pan number
     */
    public void createBankAccount(String panNumber, String type, Double amount) {

        // Negative conditions
        if (panNumber == null || panNumber.isEmpty()) {
            throw new IllegalArgumentException("Non-empty pan number is expected!");
        }

        Customer customerExists = customers.stream()
                .filter(customer -> panNumber.equals(customer.getPanNumber()))
                .findFirst()
                .orElse(null);

        String id = String.valueOf(accountId.addAndGet(1));

        BankAccount bankAccount = new SavingAccount(id, amount);

        if (type.equals("Current")) {
            bankAccount = new CurrentAccount(id, amount);
        }
        bankAccounts.add(bankAccount);

        if (customerExists != null) {
            customerExists.getAccounts().add(bankAccount);
        } else {
            // Create new customer
            Customer customer = new Customer();
            customer.setPanNumber(panNumber);
            customer.setAccounts(Stream.of(bankAccount).collect(Collectors.toList()));
            customers.add(customer);
        }
    }

    /**
     * Get customer by pan number
     *
     * @param panNumber pan number
     * @throws NoSuchRecordExistsException if for requested pan number customer doesn't exist
     */
    public Customer getCustomerByPan(String panNumber) throws NoSuchRecordExistsException {

        // Negative conditions
        if (panNumber == null || panNumber.isEmpty()) {
            throw new IllegalArgumentException("Non-empty pan number is expected!");
        }

        return customers.stream()
                .filter(customer -> panNumber.equals(customer.getPanNumber()))
                .findFirst()
                .orElseThrow(new NoSuchRecordExistsException(String.format("Customer with PAN Number: %s doesn't exist", panNumber)));
    }

    /**
     * Get bank account by account number
     *
     * @param accountNumber account number
     * @throws NoSuchRecordExistsException if for requested account number bank account doesn't exist
     */
    public BankAccount getAccountByAccountNumber(String accountNumber) throws NoSuchRecordExistsException {

        // Negative conditions
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Non-empty account number is expected!");
        }

        return bankAccounts.stream()
                .filter(bankAccount -> accountNumber.equals(bankAccount.getAccountNumber()))
                .findFirst()
                .orElseThrow(new NoSuchRecordExistsException(String.format("Bank account with account number: %s doesn't exist", accountNumber)));
    }
}
