package com.xpeppers.kata.bankaccount;

import com.xpeppers.kata.bankaccount.domain.Account;
import com.xpeppers.kata.bankaccount.domain.TransactionRepository;
import com.xpeppers.kata.bankaccount.domain.AccountService;
import com.xpeppers.kata.bankaccount.domain.TransactionStatementFormatter;
import com.xpeppers.kata.bankaccount.infrastructure.TableTransactionFormatter;
import com.xpeppers.kata.bankaccount.infrastructure.InMemoryTransactionRepository;
import com.xpeppers.kata.bankaccount.testdoubles.CurrentDateHolderStub;
import com.xpeppers.kata.bankaccount.testdoubles.PrinterSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceTest {

    private CurrentDateHolderStub currentDateHolder;
    private PrinterSpy printer;
    private TransactionRepository transactionRepository;
    private AccountService account;
    private TransactionStatementFormatter transactionFormatter;

    @BeforeEach
    void initialize() {
        currentDateHolder = new CurrentDateHolderStub();
        printer = new PrinterSpy();
        transactionRepository = new InMemoryTransactionRepository(currentDateHolder);
        transactionFormatter = new TableTransactionFormatter();
        account = new Account(transactionRepository, printer, transactionFormatter);
    }

    @Test
    void should_deposit_an_amount() {

        setCurrentDate("10/01/2012");
        account.deposit(1000);

        var transactions = transactionRepository.getAllTransactions();
        assertEquals(1, transactions.size());
        var transaction = transactions.get(0);
        assertEquals(1000, transaction.getAmount());
        assertEquals("10/01/2012", localDateToString(transaction.getDate()));
    }

    @Test
    void should_withdraw_an_amount() {

        setCurrentDate("14/01/2012");
        account.withdraw(500);

        var transactions = transactionRepository.getAllTransactions();
        assertEquals(1, transactions.size());
        var transaction = transactions.get(0);
        assertEquals(-500, transaction.getAmount());
        assertEquals("14/01/2012", localDateToString(transaction.getDate()));
    }

    @Test
    void should_not_deposit_non_positive_amount() {
        setCurrentDate("14/01/2012");
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1));
    }

    @Test
    void should_not_withdraw_non_positive_amount() {
        setCurrentDate("14/01/2012");
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-1));
    }

    @Test
    void acceptance() {
        /*

        Given a client makes a deposit of 1000 on 10-01-2012
        And a deposit of 2000 on 13-01-2012
        And a withdrawal of 500 on 14-01-2012
        When they print their bank statement
        Then they would see:

        Date       || Amount || Balance
        14/01/2012 || -500   || 2500
        13/01/2012 || 2000   || 3000
        10/01/2012 || 1000   || 1000

        */

        setCurrentDate("10/01/2012");
        account.deposit(1000);

        setCurrentDate("13/01/2012");
        account.deposit(2000);

        setCurrentDate("14/01/2012");
        account.withdraw(500);

        account.printStatement();

        var statement = printer.getPrintedString();

        var expectedStatement =
                "Date || Amount || Balance\n"  +
                "14/01/2012 || -500 || 2500\n" +
                "13/01/2012 || 2000 || 3000\n" +
                "10/01/2012 || 1000 || 1000";

        assertEquals(expectedStatement, statement);
    }

    ////////////////////////////////////////////////////////////////////////////

    private void setCurrentDate(String date) {
        currentDateHolder.setCurrentDate(stringToLocalDate(date));
    }

    private static LocalDate stringToLocalDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private static String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}