package com.superstore.useraccount.controller;

import com.superstore.useraccount.model.User;
import com.superstore.useraccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useraccount-service")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    private ResponseEntity<User> getUserDetails(Long user_id){
        User userDetails = userRepository.findById(user_id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user")
    public User updateUser(Long id, @RequestBody User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
