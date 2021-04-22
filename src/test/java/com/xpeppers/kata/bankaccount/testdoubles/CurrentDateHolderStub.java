package com.xpeppers.kata.bankaccount.testdoubles;

import com.xpeppers.kata.bankaccount.domain.CurrentDateHolder;

import java.time.LocalDate;

public class CurrentDateHolderStub implements CurrentDateHolder {

    private LocalDate currentDate;

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate date) {
        currentDate = date;
    }
}
