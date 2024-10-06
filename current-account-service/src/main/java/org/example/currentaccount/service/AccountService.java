package org.example.currentaccount.service;

import org.example.currentaccount.entity.Account;
import org.example.currentaccount.entity.Transaction;
import org.example.currentaccount.repository.AccountRepository;
import org.example.currentaccount.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(Long customerId, double initialCredit) {
        Account account = new Account(customerId, 0.0);
        accountRepository.save(account);
        
        if (initialCredit > 0) {
            Transaction transaction = new Transaction(account.getId(), initialCredit);
            transactionRepository.save(transaction);
            account.addTransaction(transaction);
        }

        return account;
    }

    public Optional<Account> findAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public List<Account> findAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}