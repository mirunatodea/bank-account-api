package org.example.currentaccount.repository

import org.example.currentaccount.entity.Transaction
import spock.lang.Specification

class TransactionRepositorySpec extends Specification {

    TransactionRepository transactionRepository = new TransactionRepository()

    def 'should save transaction successfully'() {
        given:
        Transaction transaction = new Transaction(1L, 100.0)
        
        when:
        Transaction savedTransaction = transactionRepository.save(transaction)

        then:
        savedTransaction.getId() == transaction.getId()
    }

    def 'should return null when transaction not found'() {
        given:
        Long nonExistingId = 999L

        when:
        Transaction foundTransaction = transactionRepository.findById(nonExistingId)

        then:
        foundTransaction == null
    }
}