package org.example.currentaccount.service

import org.example.currentaccount.entity.Transaction
import org.example.currentaccount.repository.TransactionRepository
import spock.lang.Specification
import spock.lang.Subject

class TransactionServiceSpec extends Specification {

    TransactionRepository transactionRepository = new TransactionRepository()

    @Subject
    TransactionService transactionService = new TransactionService(transactionRepository)

    def cleanup() {
        transactionRepository = null
        transactionService = null
    }

    def 'should create a new transaction'() {
        given: 'an existing account ID and the amount for the transaction'
        Long accountId = 1L
        double amount = 150.0
        Transaction transaction = new Transaction(1L, 150.0)

        when: 'the createTransaction method is called'
        Transaction savedTransaction = transactionService.createTransaction(accountId, amount)

        then: 'the transaction should be saved and have the correct properties'
        savedTransaction != null
        savedTransaction.getAmount() == amount
        savedTransaction.getAccountId() == accountId
    }
}

