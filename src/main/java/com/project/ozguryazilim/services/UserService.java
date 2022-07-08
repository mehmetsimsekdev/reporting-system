package com.project.ozguryazilim.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.repos.UserRepository;

@Service
public class UserService {
   UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}

public List<User> getAllUsers() {
    return userRepository.findAll();
}

public User saveOneUser(User newUser) {
    System.out.print(newUser.getId()); // delete

    return userRepository.save(newUser);
}

public User getOneUser(Long userID) {
    return userRepository.findById(userID).orElse(null);
}

public User updateOneUser(Long userId, User newUser) {
    System.out.print(newUser.getUserName()); // delete
    System.out.print(userId); // delete


    Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            System.out.print(foundUser.getUserName()); // delete
            return foundUser;
        }else 
            return null;
}

public void deleteById(Long userID) {
    userRepository.deleteById(userID);
}

public User getOneUserByUserName(String userName) {
    return userRepository.findByUserName(userName);
}
    
}
