package com.xpeppers.kata.bankaccount.infrastructure;

import com.xpeppers.kata.bankaccount.domain.CurrentDateHolder;

import java.time.LocalDate;

public class SystemCurrentDateHolder implements CurrentDateHolder {

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
