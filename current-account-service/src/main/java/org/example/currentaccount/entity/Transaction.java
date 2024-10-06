package org.example.currentaccount.entity;

import org.example.currentaccount.utils.TransactionIDGenerator;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private Long accountId;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(Long accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.id = TransactionIDGenerator.generateUniqueId();
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }
}