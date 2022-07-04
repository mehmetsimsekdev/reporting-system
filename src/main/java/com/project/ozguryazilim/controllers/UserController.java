package com.project.ozguryazilim.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ozguryazilim.services.UserService;
import com.project.ozguryazilim.entities.User;


@RestController
@RequestMapping("/users")

public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAllUsers(){
       return userService.getAllUsers();

    }
    @PostMapping("/")
    public String createUser(@RequestBody User newUser){
        userService.saveOneUser(newUser);
        return "index.html"; 

    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //custom exception
        return userService.getOneUser(userId);
    }
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser){
        return userService.updateOneUser(userId,newUser);

    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
