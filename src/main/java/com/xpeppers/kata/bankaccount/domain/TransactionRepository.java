package com.xpeppers.kata.bankaccount.domain;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> getAllTransactions();
    void addDepositTransaction(int amount);
    void addWithdrawalTransaction(int amount);
}
