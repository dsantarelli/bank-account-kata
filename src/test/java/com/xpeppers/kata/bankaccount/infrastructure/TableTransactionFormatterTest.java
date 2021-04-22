package com.xpeppers.kata.bankaccount.infrastructure;

import com.xpeppers.kata.bankaccount.domain.Transaction;
import com.xpeppers.kata.bankaccount.domain.TransactionStatementFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TableTransactionFormatterTest {

    private TransactionStatementFormatter formatter;

    @BeforeEach
    void initialize() {
        formatter = new TableTransactionFormatter();
    }

    @Test
    void should_print_only_the_table_header() {
        var output = formatter.format(Collections.emptyList());
        assertEquals("Date || Amount || Balance", output);
    }

    @Test
    void should_print_a_table_with_header_and_two_sorted_lines_with_balance() {

        var expected =
                "Date || Amount || Balance\n" +
                "21/04/2021 || -100 || 0\n" +
                "20/04/2021 || 100 || 100";

        var actual = formatter.format(Arrays.asList(
            new Transaction(100, stringToLocalDate("20/04/2021")),
            new Transaction(-100, stringToLocalDate("21/04/2021"))
        ));

        assertEquals(expected, actual);
    }

    private static LocalDate stringToLocalDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(TableTransactionFormatter.DATE_FORMAT));
    }
}