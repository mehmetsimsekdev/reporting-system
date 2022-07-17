package com.project.ozguryazilim.controllers;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.ozguryazilim.requests.UserRequest;




@Controller
public class HomeController {
    
    @GetMapping("/")
    public String signIn(@ModelAttribute("signinRequest") UserRequest signinRequest,HttpServletRequest request) {
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               if(cookie.getName().equals("isLogin") || cookie.getValue().equals("true")){
                    return "redirect:/reports";
                                    
                } 
            }
        }
        return "user/signIn";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("signupRequest") UserRequest signupRequest,HttpServletRequest request){
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               if(cookie.getName().equals("isLogin") || cookie.getValue().equals("true")){
                    return "redirect:/reports";
                                    
                } 
            }
        }
        return "user/signUp";
    }
   
    
}
