package com.xpeppers.kata.bankaccount.testdoubles;

import com.xpeppers.kata.bankaccount.domain.Printer;

public class PrinterSpy implements Printer {

    private String printedString;

    public void print(String string) {
        printedString = string;
    }

    public String getPrintedString() {
        return printedString;
    }
}
