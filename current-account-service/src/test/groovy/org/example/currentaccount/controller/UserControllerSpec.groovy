package org.example.currentaccount.controller

import org.example.currentaccount.dto.UserAccountDTO
import org.example.currentaccount.entity.Transaction
import org.example.currentaccount.entity.User
import org.example.currentaccount.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class UserControllerSpec extends Specification {

    def userService = Mock(UserService)
    def userController = new UserController(userService)

    def 'test createUser returns created status'() {
        given:
        User requestUser = new User(1L, 'Olivier', 'Hero')
        userService.create(requestUser.id, requestUser.name, requestUser.surname) >> requestUser

        when:
        ResponseEntity<User> response = userController.createUser(requestUser)

        then:
        response.statusCode == HttpStatus.CREATED
    }

    def 'test createUser returns correct user'() {
        given:
        User requestUser = new User(1L, 'Olivier', 'Hero')
        userService.create(requestUser.id, requestUser.name, requestUser.surname) >> requestUser

        when:
        ResponseEntity<User> response = userController.createUser(requestUser)

        then:
        response.body == requestUser
    }

    def 'test getUserInfo returns ok status'() {
        given:
        Long userId = 1L
        User user = new User(userId, 'Olivier', 'Hero')
        List<Transaction> transactions = Mock()
        UserAccountDTO userDTO = new UserAccountDTO(
                name: user.name,
                surname: user.surname,
                balance: 100.0,
                transactions: transactions
        )
        userService.findById(userId) >> Optional.of(user)

        userService.getUserInfo(user) >> userDTO

        when:
        ResponseEntity<UserAccountDTO> response = userController.getUserInfo(userId)

        then:
        response.statusCode == HttpStatus.OK
        response.body == userDTO
    }
}
