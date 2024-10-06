package org.example.currentaccount.controller;

import org.example.currentaccount.dto.UserAccountDTO;
import org.example.currentaccount.entity.User;
import org.example.currentaccount.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User requestUser) {
        if (requestUser == null) {
            logger.info("Received null user, cannot create user");
            return ResponseEntity.badRequest().body(null);
        }

        if (requestUser.getId() == null) {
            logger.info("User id cannot be null");
            return ResponseEntity.badRequest().body(null);
        }

        User user = userService.create(requestUser.getId(), requestUser.getName(), requestUser.getSurname());
        logger.info("User created with surname: {}", user.getSurname());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> getUserInfo(@PathVariable Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            logger.info("User not found for customerId: {}", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        User user = userOptional.get();
        logger.info("Retrieving user information for userId: {}", userId);
        UserAccountDTO userInfo = userService.getUserInfo(user);
        return ResponseEntity.ok(userInfo);
    }
}
