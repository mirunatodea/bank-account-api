package org.example.currentaccount.service

import org.example.currentaccount.dto.UserAccountDTO
import org.example.currentaccount.entity.Account
import org.example.currentaccount.entity.User
import org.example.currentaccount.repository.UserRepository
import spock.lang.Specification

class UserServiceSpec extends Specification {

    UserRepository userRepository = Mock()
    AccountService accountService = Mock()
    UserService userService = new UserService(userRepository, accountService)

    def 'should return user when user exists'() {
        given: 'a user with an existing ID in the repository'
        Long userId = 1L
        User user = new User(userId, 'Mick', 'Mike')
        userRepository.findById(userId) >> Optional.of(user)

        when: 'findById is called with the user ID'
        Optional<User> result = userService.findById(userId)

        then: 'the returned optional contains the user'
        result.isPresent()
        result.get() == user
    }

    def 'should return empty optional when user does not exist'() {
        given: 'a non-existing user ID'
        Long userId = 1L
        userRepository.findById(userId) >> Optional.empty()

        when: 'findById is called with the non-existing user ID'
        Optional<User> result = userService.findById(userId)

        then: 'the returned optional is empty'
        !result.isPresent()
    }

    def 'should return correct user account DTO'() {
        given: 'a user and associated accounts in the repository'
        Long userId = 1L
        User user = new User(userId, 'Mick', 'Der')
        Account account1 = new Account(userId, 100.0)
        account1.setId(1L)
        Account account2 = new Account(userId, 200.0)
        account2.setId(2L)
        userRepository.findById(userId) >> Optional.of(user)
        accountService.findAccountsByCustomerId(userId) >> [account1, account2]

        when:
        UserAccountDTO userInfo = userService.getUserInfo(user)

        then: 'the returned user info has the correct balance'
        userInfo.getBalance() == 300.0
    }

    def 'should store user correctly'() {
        given: 'a user ID, name, and surname'
        Long userId = 1L
        String name = 'Peni'
        String surname = 'Pani'
        User user = new User(userId, name, surname)
        userRepository.save(_) >> user

        when: 'create is called'
        User result = userService.create(userId, name, surname)

        then: 'the returned user is correctly created and stored'
        result == user
    }
}
