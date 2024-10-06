package org.example.currentaccount.entity

import spock.lang.Specification

class AccountSpec extends Specification {

    def 'should get customer ID'() {
        given: 'an account with a specific customer ID'
        Long customerId = 1L
        Account account = new Account(customerId, 0.0)

        expect: 'the getter for customer ID returns the correct value'
        account.getCustomerID() == customerId
    }

    def 'should get account balance'() {
        given: 'an account with an initial balance'
        Account account = new Account(1L, 150.0)

        expect: 'the getter for balance returns the correct value'
        account.getBalance() == 150.0
    }

    def 'should get account ID'() {
        given: 'an account with a unique ID'
        Account account = new Account(1L, 0.0)

        expect: 'the getter for account ID returns a non-null value'
        account.getId() != null
    }

    def 'should add a new transaction to account'() {
        given: 'an account with an initial balance of 0'
        Account account = new Account(1L, 0.0)
        Transaction transaction = new Transaction(account.getId(), 50.0)

        when: 'a new transaction is added'
        account.addTransaction(transaction)

        then: 'the account contains the new transaction'
        account.transactions.containsKey(transaction.getId())
    }

    def 'should update the balance when adding a transaction'() {
        given: 'an account with an initial balance of 0'
        Account account = new Account(1L, 0.0)
        Transaction transaction = new Transaction(account.getId(), 50.0)

        when: 'a new transaction is added'
        account.addTransaction(transaction)

        then: 'the balance is updated'
        account.getBalance() == 50.0
    }
}

