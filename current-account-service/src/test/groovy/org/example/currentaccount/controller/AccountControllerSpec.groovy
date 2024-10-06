package org.example.currentaccount.controller

import org.example.currentaccount.dto.AccountDTO
import org.example.currentaccount.entity.Account
import org.example.currentaccount.entity.User
import org.example.currentaccount.service.AccountService
import org.example.currentaccount.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class AccountControllerSpec extends Specification {

    AccountService accountService = Mock()
    UserService userService = Mock()
    AccountController accountController = new AccountController(accountService, userService)

    def 'should return created status'() {
        given: 'a valid user and account request'
        Long customerId = 1L
        User user = new User(customerId, "Pani", "Peni")

        AccountDTO requestAccount = new AccountDTO(
                customerId: customerId,
                initialCredit: 100.0
        )
        Account account = new Account(customerId, 100.0)

        userService.findById(customerId) >> Optional.of(user)
        accountService.createAccount(customerId, 100.0) >> account

        when: 'creating an account for the user'
        ResponseEntity<Account> response = accountController.createAccount(requestAccount)

        then: 'the response status is CREATED'
        response.statusCode == HttpStatus.CREATED
    }

    def 'should return not found when user does not exist'() {
        given: 'a non-existent user'
        Long customerId = 1L
        AccountDTO requestAccount = new AccountDTO(
                customerId: customerId,
                initialCredit: 100.0
        )

        userService.findById(customerId) >> Optional.empty()

        when: 'creating an account for the non-existent user'
        ResponseEntity<Account> response = accountController.createAccount(requestAccount)

        then: 'the response status is NOT FOUND'
        response.statusCode == HttpStatus.NOT_FOUND
    }

    def 'should return ok status when account id found'() {
        given: 'an existing account'
        Long accountId = 1L
        Account account = new Account(1L, 100.0)
        account.setId(accountId)

        accountService.findAccountById(accountId) >> Optional.of(account)

        when: 'retrieving the account by ID'
        ResponseEntity<Account> response = accountController.getAccountById(accountId)

        then: 'the response status is OK'
        response.statusCode == HttpStatus.OK
    }

    def 'should return not found when account does not exist'() {
        given: 'a non-existent account'
        Long accountId = 1L

        accountService.findAccountById(accountId) >> Optional.empty()

        when: 'retrieving the non-existent account by ID'
        ResponseEntity<Account> response = accountController.getAccountById(accountId)

        then: 'the response status is NOT FOUND'
        response.statusCode == HttpStatus.NOT_FOUND
    }
}
