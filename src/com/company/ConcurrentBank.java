package com.company;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ConcurrentBank {
    private Map<Integer, BankAccount> bankAccounts = new HashMap<>();

    public BankAccount createAccount(BigDecimal balance) {
        BankAccount bankAccount = new BankAccount(balance);
        bankAccounts.put(bankAccount.getUniqueId(), bankAccount);
        return bankAccount;
    }

    public synchronized void transfer(BankAccount sendersAccount, BankAccount recipientAccount, BigDecimal amount) {
        if (sendersAccount == null || recipientAccount == null) {
            throw new IllegalArgumentException("Accounts cannot be null");
        }
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (sendersAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient founds");
        }

        sendersAccount.setBalance(sendersAccount.getBalance().subtract(amount));
        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));
    }

    public BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (BankAccount account : bankAccounts.values()) {
            totalBalance = totalBalance.add(account.getBalance());
        }
        return totalBalance;
    }

}
