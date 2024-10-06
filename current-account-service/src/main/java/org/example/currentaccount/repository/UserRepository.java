package org.example.currentaccount.repository;

import org.example.currentaccount.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<Long, User> userStorage = new HashMap<>();

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    public User save(User user) {
        userStorage.put(user.getId(), user);
        return user;
    }
}
