package com.xpeppers.kata.bankaccount.domain;

import java.time.LocalDate;

public class Transaction {

    private final int amount;
    private final LocalDate date;

    public Transaction(int amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
