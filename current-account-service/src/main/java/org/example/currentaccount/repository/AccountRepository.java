package org.example.currentaccount.repository;

import org.example.currentaccount.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccountRepository {
    private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);
    private final Map<Long, Account> accounts = new HashMap<>();

    public Account save(Account account) {
        if (account == null) {
            logger.info("Account cannot be null");
            return null;
        }
        accounts.put(account.getId(), account);
        return account;
    }

    public Optional<Account> findById(Long accountId) { //optional - avoid nullpointerexception, and ofers us the possibility to use isPresent()
        return Optional.ofNullable(accounts.get(accountId));
    }

    public boolean existsById(Long accountId) {
        if (accountId == null) {
            logger.info("Account ID cannot be null");
        }
        return accounts.containsKey(accountId);
    }

    public List<Account> findByCustomerId(Long customerId) {
        if (customerId == null) {
            logger.info("Customer ID cannot be null");
        }
        return accounts.values().stream()
                .filter(account -> Objects.equals(account.getCustomerID(), customerId))
                .collect(Collectors.toList());
    }
}