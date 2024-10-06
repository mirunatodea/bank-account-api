package org.example.currentaccount.service;

import org.example.currentaccount.entity.Transaction;
import org.example.currentaccount.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Long accountId, double amount) {
        Transaction transaction = new Transaction(accountId, amount);
        return transactionRepository.save(transaction);
    }
}