package org.example.currentaccount.repository

import org.example.currentaccount.entity.Account
import spock.lang.Specification

class AccountRepositorySpec extends Specification {

    AccountRepository accountRepository

    def setup() {
        accountRepository = new AccountRepository()
    }

    def 'should save a new account'() {
        given:
        Account account = new Account(1L, 100.0)

        when:
        Account savedAccount = accountRepository.save(account)

        then:
        savedAccount.id != null
        savedAccount.customerID == account.customerID
        savedAccount.balance == account.balance
    }

    def 'should find an account by ID'() {
        given:
        Account account = new Account(1L, 100.0)
        account = accountRepository.save(account)

        when:
        Optional<Account> foundAccount = accountRepository.findById(account.id)

        then:
        foundAccount.isPresent()
        foundAccount.get().id == account.id
        foundAccount.get().customerID == account.customerID
    }

    def 'should return empty optional when account not found'() {
        when:
        Optional<Account> foundAccount = accountRepository.findById(999L)

        then:
        !foundAccount.isPresent()
    }

    def 'should check if account exists by ID'() {
        given:
        Account account = new Account(1L, 100.0)
        account = accountRepository.save(account)

        expect:
        accountRepository.existsById(account.id)
        !accountRepository.existsById(999L)
    }

    def 'should find all accounts by customer ID'() {
        given:
        Account account1 = new Account(1L, 100.0)
        Account account2 = new Account(1L, 200.0)
        Account account3 = new Account(2L, 300.0)
        accountRepository.save(account1)
        accountRepository.save(account2)
        accountRepository.save(account3)

        when:
        List<Account> customerAccounts = accountRepository.findByCustomerId(1L)

        then:
        customerAccounts.size() == 2
        customerAccounts*.customerID.unique().contains(1L)
    }

    def 'should return an empty list if no accounts found for customer ID'() {
        when:
        List<Account> customerAccounts = accountRepository.findByCustomerId(999L)

        then:
        customerAccounts.isEmpty()
    }
}