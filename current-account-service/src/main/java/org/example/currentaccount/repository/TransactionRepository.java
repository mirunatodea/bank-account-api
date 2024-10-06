package org.example.currentaccount.repository;

import org.example.currentaccount.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TransactionRepository {
    private final Map<Long, Transaction> transactions = new HashMap<>();

    public Transaction save(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    public Transaction findById(Long transactionId) {
        return transactions.get(transactionId);
    }
}