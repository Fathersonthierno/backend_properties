package com.graduation.backend_properties.controller;

import com.graduation.backend_properties.modele.User;
import com.graduation.backend_properties.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/${api-version}/user")
public class Usercontroller {
    private final UserRepository userRepository;

    public Usercontroller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping
    public User adduser(@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping
    public List<User> findAllUser(User user){
        return userRepository.findAll() ;
    }
    @GetMapping("/id")
    public Optional<User> findById(@PathVariable Long id){
        return userRepository.findById(id) ;
    }
    @DeleteMapping("/id")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
    @PutMapping
    public User upadateUser(@PathVariable Long id , @RequestBody User user) {
        return userRepository.save(user) ;
    }
}
