package org.milan.banking;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Test cases for Banking Application
 *
 * @author Milan Rathod
 */
public class BankApplicationTest {

    private static HeadOffice headOffice;

    private static Branch branch;

    @BeforeClass
    public static void init() {
        headOffice = new HeadOffice();
        headOffice.createBranch();
        headOffice.createBranch();

        branch = headOffice.getBranchById("1");
        branch.createBankAccount("test_PAN_1", "Saving", 20000.0);
        branch.createBankAccount("test_PAN_1", "Current", 10000.0);
    }

    @Test
    public void testBranches() {
        Assert.assertEquals(2, headOffice.getAllBranches().size());
    }

    @Test
    public void test_getCustomerByPan_Success() throws NoSuchRecordExistsException {
        Customer customer = branch.getCustomerByPan("test_PAN_1");

        Assert.assertNotNull(customer);
    }

    @Test
    public void test_getCustomerByPan_Exception() {
        try {
            branch.getCustomerByPan("test_invalid_pan");
            Assert.fail();
        } catch (NoSuchRecordExistsException ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_getAccountByAccountNumber_Success() throws NoSuchRecordExistsException {
        BankAccount bankAccount = branch.getAccountByAccountNumber("2");

        Assert.assertNotNull(bankAccount);
    }

    @Test
    public void test_getAccountByAccountNumber_Exception() {
        try {
            branch.getAccountByAccountNumber("3");
            Assert.fail();
        } catch (NoSuchRecordExistsException ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testTransactions() throws NoSuchRecordExistsException {
        BankAccount bankAccount = branch.getAccountByAccountNumber("1");

        bankAccount.deposit(100.0);
        bankAccount.deposit(200.0);
        bankAccount.deposit(300.0);
        bankAccount.deposit(400.0);
        bankAccount.deposit(500.0);
        bankAccount.deposit(600.0);
        bankAccount.deposit(700.0);
        bankAccount.deposit(800.0);
        bankAccount.deposit(900.0);
        bankAccount.deposit(1000.0);
        bankAccount.deposit(1100.0);
        bankAccount.deposit(1200.0);
        bankAccount.withdraw(500.0);
        bankAccount.withdraw(250.0);

        List<Transaction> transactionHistory = bankAccount.getTransactionHistory();

        Assert.assertEquals(14, transactionHistory.size());

        List<Transaction> miniStatement = bankAccount.getMiniStatement();

        Assert.assertEquals(10, miniStatement.size());

        Assert.assertEquals(0, miniStatement.get(9).getAmount().compareTo(250.0d));
    }

}