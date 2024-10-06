package org.example.currentaccount.service;

import org.example.currentaccount.dto.UserAccountDTO;
import org.example.currentaccount.entity.Account;
import org.example.currentaccount.entity.Transaction;
import org.example.currentaccount.entity.User;
import org.example.currentaccount.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public UserAccountDTO getUserInfo(User user) {
        List<Account> accounts = accountService.findAccountsByCustomerId(user.getId());
        double balance = accounts.stream().mapToDouble(Account::getBalance).sum();
        List<Transaction> transactions = accounts.stream()
                .flatMap(account -> account.getTransactions().values().stream())
                .collect(Collectors.toList());

        UserAccountDTO userDTO = new UserAccountDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setBalance(balance);
        userDTO.setTransactions(transactions);

        return userDTO;
    }

    public User create(Long id, String name, String surname) {
        User user = new User(id, name, surname);
        return userRepository.save(user);
    }
}
