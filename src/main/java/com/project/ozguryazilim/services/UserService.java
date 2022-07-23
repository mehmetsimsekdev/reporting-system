package com.project.ozguryazilim.services;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ozguryazilim.entities.Role;
import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.repos.UserRepository;
import com.project.ozguryazilim.security.JwtTokenProvider;

@Service
public class UserService {
   UserRepository userRepository;
   private AuthenticationManager authenticationManager;
   private JwtTokenProvider jwtTokenProvider;
   private PasswordEncoder passwordEncoder;
   private RefreshTokenService refreshTokenService;



public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,JwtTokenProvider jwtTokenProvider,PasswordEncoder passwordEncoder,RefreshTokenService refreshTokenService) {
    this.userRepository = userRepository;
    this.authenticationManager=authenticationManager;
    this.jwtTokenProvider=jwtTokenProvider;
    this.passwordEncoder= passwordEncoder;
    this.refreshTokenService=refreshTokenService;
    
    
}


public List<User> getAllUsers() {
    return userRepository.findAll();
}

public User saveOneUser(User newUser) {

    return userRepository.save(newUser);
}

public User getOneUser(Long userID) {
    return userRepository.findById(userID).orElse(null);
}

public User updateOneUser(Long userId, User newUser) throws UnsupportedEncodingException {

    Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            foundUser.setName(newUser.getName());            
            userRepository.save(foundUser);
            return foundUser;
        }else 
            return null;
}

public void editOneUserRole(Long userId, Role newUserRole){
    Optional<User> user = userRepository.findById(userId);
    if(user.isPresent()){
        User foundUser= user.get();
        foundUser.setRole(newUserRole);
        userRepository.save(foundUser);


    }
}

public void deleteById(Long userId) {
    try {
    userRepository.deleteById(userId);
    }catch(EmptyResultDataAccessException e) { 
        System.out.println("User "+userId+" doesn't exist"); 
    }
}

public User getOneUserByUserName(String userName) {
    return userRepository.findByUserName(userName);
}
public User getOneUserById(Long userId) {
    return userRepository.findById(userId).orElse(null);
}
    
}
