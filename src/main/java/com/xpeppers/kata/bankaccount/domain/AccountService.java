package com.xpeppers.kata.bankaccount.domain;

public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}
