package org.example.currentaccount.entity

import spock.lang.Specification

class UserSpec extends Specification {

    def 'should get user ID'() {
        given: 'a user with a specific ID'
        Long id = 1L
        User user = new User(id, 'M', 'T')

        expect: 'getId returns the correct ID'
        user.getId() == id
    }

    def 'should get user name'() {
        given: 'a user with a specific name'
        String name = 'M'
        User user = new User(1L, name, 'T')

        expect: 'getName returns the correct name'
        user.getName() == name
    }

    def 'should get user surname'() {
        given: 'a user with a specific surname'
        String surname = 'T'
        User user = new User(1L, 'M', surname)

        expect: 'getSurname returns the correct surname'
        user.getSurname() == surname
    }

    def 'should update with a new name'() {
        given: 'a user with an initial name'
        User user = new User(1L, 'M', 'T')

        when: 'the name is updated'
        String newName = 'Jane'
        user.setName(newName)

        then: 'getName returns the updated name'
        user.getName() == newName
    }

    def 'should update with a new surname'() {
        given: 'a user with an initial surname'
        User user = new User(1L, 'M', 'T')

        when: 'the surname is updated'
        String newSurname = 'Smith'
        user.setSurname(newSurname)

        then: 'getSurname returns the updated surname'
        user.getSurname() == newSurname
    }
}
