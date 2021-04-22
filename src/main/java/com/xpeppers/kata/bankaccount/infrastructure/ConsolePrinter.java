package com.xpeppers.kata.bankaccount.infrastructure;

import com.xpeppers.kata.bankaccount.domain.Printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(String string) {
        System.out.print(string);
    }
}
