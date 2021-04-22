package com.xpeppers.kata.bankaccount.infrastructure;

import com.xpeppers.kata.bankaccount.domain.Transaction;
import com.xpeppers.kata.bankaccount.domain.TransactionStatementFormatter;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TableTransactionFormatter implements TransactionStatementFormatter {

    protected static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public String format(List<Transaction> transactions) {

        var lines = new ArrayList<String>();
        var currentBalance = 0;

        var sortedTransactions = transactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .collect(Collectors.toList());

        for (var transaction: sortedTransactions) {
            currentBalance += transaction.getAmount();
            lines.add(formatLine(transaction, currentBalance));
        }

        Collections.reverse(lines);

        lines.add(0, "Date || Amount || Balance");

        return lines.stream().collect(Collectors.joining("\n"));
    }

    private static String formatLine(Transaction transaction, int currentBalance) {
        return MessageFormat.format("{0} || {1} || {2}",
                localDateToString(transaction.getDate()),
                String.valueOf(transaction.getAmount()),
                String.valueOf(currentBalance));
    }

    private static String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
