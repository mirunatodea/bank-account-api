package org.example.currentaccount.dto

import org.example.currentaccount.entity.Transaction
import spock.lang.Specification

class UserAccountDTOSpec extends Specification {

    def userAccountDTO

    def setup() {
        userAccountDTO = new UserAccountDTO()
    }

    def 'should set name correctly'() {
        given:
        String name = 'Miruna'

        when:
        userAccountDTO.setName(name)

        then:
        userAccountDTO.getName() == name
    }

    def 'should get the correct name'() {
        given:
        String name = 'Miruna'
        userAccountDTO.setName(name)

        when:
        String result = userAccountDTO.getName()

        then:
        result == name
    }

    def 'should set the surname correctly'() {
        given:
        String surname = 'Todea'

        when:
        userAccountDTO.setSurname(surname)

        then:
        userAccountDTO.getSurname() == surname
    }

    def 'should get surname correctly'() {
        given:
        String surname = 'Todea'
        userAccountDTO.setSurname(surname)

        when:
        String result = userAccountDTO.getSurname()

        then:
        result == surname
    }

    def 'should set balance'() {
        given:
        double balance = 500.0

        when:
        userAccountDTO.setBalance(balance)

        then:
        userAccountDTO.getBalance() == balance
    }

    def 'should get balance'() {
        given:
        double balance = 750.0
        userAccountDTO.setBalance(balance)

        when:
        double result = userAccountDTO.getBalance()

        then:
        result == balance
    }

    def 'should set transactions'() {
        given:
        List<Transaction> transactions = [new Transaction(1L, 100.0), new Transaction(1L, 200.0)]

        when:
        userAccountDTO.setTransactions(transactions)

        then:
        userAccountDTO.getTransactions() == transactions
    }

    def 'should return transactions'() {
        given:
        List<Transaction> transactions = [new Transaction(1L, 150.0)]
        userAccountDTO.setTransactions(transactions)

        when:
        List<Transaction> result = userAccountDTO.getTransactions()

        then:
        result == transactions
    }
}
