package org.example.currentaccount.dto

import spock.lang.Specification

class AccountDTOSpec extends Specification {

    def accountDTO

    def setup() {
        accountDTO = new AccountDTO()
    }

    def 'should set the customer id correctly'() {
        given:
        long customerId = 1L

        when:
        accountDTO.setCustomerId(customerId)

        then:
        accountDTO.getCustomerId() == customerId
    }

    def 'should set the initial credit correctly'() {
        given:
        double initialCredit = 100.0

        when:
        accountDTO.setInitialCredit(initialCredit)

        then:
        accountDTO.getInitialCredit() == initialCredit
    }

    def 'should return the customer id correctly'() {
        given:
        Long customerId = 2L
        accountDTO.setCustomerId(customerId)

        when:
        Long result = accountDTO.getCustomerId()

        then:
        result == customerId
    }

    def 'should return the initial credit correctly'() {
        given:
        double initialCredit = 200.0
        accountDTO.setInitialCredit(initialCredit)

        when:
        double result = accountDTO.getInitialCredit()

        then:
        result == initialCredit
    }
}
