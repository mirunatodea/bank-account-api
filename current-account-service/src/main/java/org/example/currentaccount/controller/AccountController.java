package org.example.currentaccount.controller;

import org.example.currentaccount.dto.AccountDTO;
import org.example.currentaccount.entity.Account;
import org.example.currentaccount.entity.User;
import org.example.currentaccount.service.AccountService;
import org.example.currentaccount.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO requestAccount) {
        Optional<User> userOptional = userService.findById(requestAccount.getCustomerId());
        if (userOptional.isEmpty()) {
            logger.info("User not found for customerId: {}", requestAccount.getCustomerId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Account account = accountService.createAccount(requestAccount.getCustomerId(), requestAccount.getInitialCredit());
        logger.info("Account created for customerId: {}", requestAccount.getCustomerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        return accountService.findAccountById(accountId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.info("Account not found for accountId: {}", accountId);
                    return ResponseEntity.notFound().build();
                });
    }
}
