package org.example.currentaccount.repository

import org.example.currentaccount.entity.User
import spock.lang.Specification

class UserRepositorySpec extends Specification {

    def 'should return user when user exists'() {
        given: 'a user repository with a stored user'
        UserRepository userRepository = new UserRepository()
        User user = new User(1L, 'Deo', 'Doe')
        userRepository.save(user)

        when: 'findById is called with an existing ID'
        Optional<User> result = userRepository.findById(1L)

        then: 'findById returns the user'
        result.isPresent()
        result.get() == user
    }

    def 'should returns empty when user does not exist'() {
        given: 'an empty user repository'
        UserRepository userRepository = new UserRepository()

        when: 'findById is called with a non-existing ID'
        Optional<User> result = userRepository.findById(1L)

        then: 'findById returns an empty optional'
        !result.isPresent()
    }

    def 'should save user correctly'() {
        given:
        UserRepository userRepository = new UserRepository()
        User user = new User(1L, 'Deo', 'Doe')

        when: 'the user is saved'
        User savedUser = userRepository.save(user)

        then: 'the saved user is returned'
        savedUser == user
    }

    def 'test save updates existing user'() {
        given: 'a user repository with a stored user'
        UserRepository userRepository = new UserRepository()
        User user = new User(1L, 'Deo', 'Doe')
        userRepository.save(user)

        when: 'the user is updated and saved again'
        user.setName('Pou')
        User updatedUser = userRepository.save(user)

        then: 'the updated user is returned'
        updatedUser.getName() == 'Pou'
    }
}

