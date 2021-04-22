package com.xpeppers.kata.bankaccount.domain;

import java.util.List;

public interface TransactionStatementFormatter {
    String format(List<Transaction> transactions);
}
