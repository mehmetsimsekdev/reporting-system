package com.project.ozguryazilim.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.ozguryazilim.services.ReportService;
import com.project.ozguryazilim.services.UserService;
import com.project.ozguryazilim.entities.Report;
import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.exceptions.UserNotFoundException;
import com.project.ozguryazilim.responses.UserResponse;


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
    @PostMapping
	public ResponseEntity<Void> createUser(@RequestBody User newUser) {
		User user = userService.saveOneUser(newUser);
		if(user != null) 
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @GetMapping("/{userId}")
	public ModelAndView getOneUser(@PathVariable Long userId, User newUser,Model model) {
		User user = userService.getOneUserById(userId);
		System.out.println(user);
		model.addAttribute("currentUser", user);
		model.addAttribute("userId", userId);
		if(user == null) {
			throw new UserNotFoundException();
		}
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/accountDetails");
		return modelAndView;
	}

    @PostMapping("/{userId}")
	public void updateOneUser(@PathVariable Long userId, @ModelAttribute("updatedUser") User newUser,HttpServletResponse response) throws IOException {
		System.out.println("aaaaaaaaaaaa");
		User user = userService.updateOneUser(userId, newUser, response);
		if(user != null) 
			 response.sendRedirect("/reports");
		else response.sendRedirect("/users/"+userId);

	}

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void handleUserNotFound() {
		
	}
}
