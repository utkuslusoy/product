package com.example.product.controller;

import com.example.product.user.User;
import com.example.product.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> save (@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/find")
    public ResponseEntity<?> find (@RequestBody User user) {
        User userInfo = userService.find(user);
        return userInfo != null ? ResponseEntity.ok(userInfo) : ResponseEntity.notFound().build();
    }

}
