package com.xpeppers.kata.bankaccount;

import com.xpeppers.kata.bankaccount.domain.Account;
import com.xpeppers.kata.bankaccount.infrastructure.ConsolePrinter;
import com.xpeppers.kata.bankaccount.infrastructure.InMemoryTransactionRepository;
import com.xpeppers.kata.bankaccount.infrastructure.SystemCurrentDateHolder;
import com.xpeppers.kata.bankaccount.infrastructure.TableTransactionFormatter;

public class App {
    public static void main(String[] args) {

        var currentDateHolder = new SystemCurrentDateHolder();
        var transactionRepository = new InMemoryTransactionRepository(currentDateHolder);
        var printer = new ConsolePrinter();
        var transactionFormatter = new TableTransactionFormatter();

        var account = new Account(transactionRepository, printer, transactionFormatter);

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();
    }
}
