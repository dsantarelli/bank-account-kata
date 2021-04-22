package com.xpeppers.kata.bankaccount.infrastructure;

import com.xpeppers.kata.bankaccount.domain.CurrentDateHolder;
import com.xpeppers.kata.bankaccount.domain.Transaction;
import com.xpeppers.kata.bankaccount.domain.TransactionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Transaction> transactions;
    private final CurrentDateHolder currentDateHolder;

    public InMemoryTransactionRepository(CurrentDateHolder currentDateHolder) {
        this.transactions = new ArrayList<>();
        this.currentDateHolder = currentDateHolder;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public void addDepositTransaction(int amount) {
        transactions.add(new Transaction(amount, currentDateHolder.getCurrentDate()));
    }

    @Override
    public void addWithdrawalTransaction(int amount) {
        transactions.add(new Transaction(-amount, currentDateHolder.getCurrentDate()));
    }
}
