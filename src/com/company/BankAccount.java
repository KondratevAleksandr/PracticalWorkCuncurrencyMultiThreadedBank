package com.company;

import java.math.BigDecimal;
import java.util.Random;

public class BankAccount {
    Random random = new Random();

    private final Integer uniqueId;
    private BigDecimal balance;

    public BankAccount(BigDecimal balance) {
        this.uniqueId = 1000 + random.nextInt(9000);
        this.balance = balance;
    }

    public synchronized void deposit(Integer id, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public synchronized void withdraw(int id, BigDecimal amount) {
        idVerification(id);
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient founds");
        }
        this.balance = this.balance.subtract(amount);
    }


    public int getUniqueId() {
        return uniqueId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public synchronized void setBalance(BigDecimal amount) {
        this.balance = amount;
    }

    private boolean idVerification(Integer id) {
        if (!uniqueId.equals(id))
            System.out.println("Wrong account number! Please enter the correct account number");
        return uniqueId.equals(id);
    }
}
