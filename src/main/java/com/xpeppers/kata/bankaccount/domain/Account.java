package com.xpeppers.kata.bankaccount.domain;

public class Account implements AccountService {

    private final TransactionRepository repository;
    private final TransactionStatementFormatter transactionFormatter;
    private final Printer printer;

    public Account(
            TransactionRepository repository,
            Printer printer,
            TransactionStatementFormatter transactionFormatter) {

        this.repository = repository;
        this.printer = printer;
        this.transactionFormatter = transactionFormatter;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Please deposit a valid amount");
        repository.addDepositTransaction(amount);
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Please withdraw a valid amount");
        repository.addWithdrawalTransaction(amount);
    }

    @Override
    public void printStatement() {
        printer.print(transactionFormatter.format(repository.getAllTransactions()));
    }
}
