package com.example.product.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User find(User user) {
        Optional<User> userOptional = userRepository.findUser(user.getName(), user.getSurname());
        return userOptional.orElse(null);
    }

    public boolean isUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

}
