package org.example.currentaccount.entity

import spock.lang.Specification

class TransactionSpec extends Specification {

    def 'should get transaction ID'() {
        given: 'a transaction with a specific ID'
        Long accountId = 1L
        double amount = 100.0
        Transaction transaction = new Transaction(accountId, amount)

        expect: 'the getter for transaction ID returns a non-null value'
        transaction.getId() != null
    }

    def 'should get account ID from transaction'() {
        given: 'a transaction with a specific account ID'
        Long accountId = 1L
        double amount = 100.0
        Transaction transaction = new Transaction(accountId, amount)

        expect: 'the getter for account ID returns the correct value'
        transaction.getAccountId() == accountId
    }

    def 'should get transaction amount'() {
        given: 'a transaction with a specific amount'
        Long accountId = 1L
        double amount = 100.0
        Transaction transaction = new Transaction(accountId, amount)

        expect: 'the getter for transaction amount returns the correct value'
        transaction.getAmount() == amount
    }
}
