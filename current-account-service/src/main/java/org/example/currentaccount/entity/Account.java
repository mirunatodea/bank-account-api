package org.example.currentaccount.entity;

import org.example.currentaccount.utils.AccountIDGenerator;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private Long id;
    private Long customerID; //this corresponds to User ID
    private double balance;
    private Map<Long, Transaction> transactions;

    public Account(Long customerID, double initialCredit) {
        this.id = AccountIDGenerator.generateUniqueId();
        this.customerID = customerID;
        this.balance = 0.0;
        this.transactions = new HashMap<>();

        if (initialCredit > 0) {
            Transaction initialTransaction = new Transaction(this.id, initialCredit);
            addTransaction(initialTransaction);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Long, Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null || transactions.containsKey(transaction.getId())) {
            throw new IllegalArgumentException("Invalid transaction or transaction already exists");
        }
        this.transactions.put(transaction.getId(), transaction);
        updateBalance(transaction);
    }

    public void updateBalance(Transaction transaction) {
        this.balance += transaction.getAmount();
    }

    public Long getCustomerId() {
        return this.customerID;
    }
}
