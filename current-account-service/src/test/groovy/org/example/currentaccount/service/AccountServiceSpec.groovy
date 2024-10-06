package org.example.currentaccount.service

import org.example.currentaccount.entity.Account
import org.example.currentaccount.repository.AccountRepository
import org.example.currentaccount.repository.TransactionRepository
import spock.lang.Specification

class AccountServiceSpec extends Specification {

    AccountService accountService
    AccountRepository accountRepository
    TransactionRepository transactionRepository

    def setup() {
        accountRepository = Mock(AccountRepository)
        transactionRepository = Mock(TransactionRepository)
        accountService = new AccountService(accountRepository, transactionRepository)
    }

    def cleanup() {
        accountRepository = null
        transactionRepository = null
        accountService = null
    }

    def 'should create a new account with initial credit and transaction'() {
        given: 'a customer ID and initial credit'
        Long customerId = 1L
        double initialCredit = 100.0

        when: 'the createAccount method is called'
        Account createdAccount = accountService.createAccount(customerId, initialCredit)

        then: 'the account should be created with the correct customer ID and balance'
        createdAccount.getCustomerID() == customerId

        and: 'the initial balance should match the provided initial credit'
        createdAccount.getBalance() == initialCredit

        and: 'the transaction should be saved in the transaction repository'
        1 * transactionRepository.save(_)
    }

    def 'should create a new account without transaction when initial credit is zero'() {
        given: 'a customer ID and zero initial credit'
        Long customerId = 1L
        double initialCredit = 0.0

        when: 'the createAccount method is called'
        Account createdAccount = accountService.createAccount(customerId, initialCredit)

        then: 'the account should be created with the correct customer ID'
        createdAccount.getCustomerID() == customerId

        and: 'the balance should be zero'
        createdAccount.getBalance() == initialCredit

        and: 'no transaction should be saved'
        0 * transactionRepository.save(_)
    }

    def 'should find an account by ID'() {
        given: 'an account ID and an existing account'
        Long accountId = 1L
        Long customerId = 1L
        double initialCredit = 200.0

        Account account = new Account(customerId, initialCredit)

        accountRepository.findById(accountId) >> Optional.of(account)

        when: 'the findAccountById method is called'
        Optional<Account> foundAccount = accountService.findAccountById(accountId)

        then: 'the account should be present and match the expected account'
        foundAccount.isPresent()
        foundAccount.get().getId() == account.getId()
        foundAccount.get().getCustomerID() == customerId
        foundAccount.get().getBalance() == initialCredit
    }


    def 'should return empty optional when account is not found'() {
        given: 'an account ID that does not exist'
        Long accountId = 999L

        accountRepository.findById(accountId) >> Optional.empty()

        when: 'the findAccountById method is called'
        Optional<Account> foundAccount = accountService.findAccountById(accountId)

        then: 'the account should not be present'
        !foundAccount.isPresent()
    }
}
