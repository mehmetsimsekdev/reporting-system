package com.project.ozguryazilim.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class CookieService {
    

    public void addCookie(String name,String value,int maxAge,HttpServletResponse response){
        Cookie newCookie = new Cookie(name, value);
        newCookie.setMaxAge(maxAge);
        newCookie.setPath("/");
        response.addCookie(newCookie);
    }
    
    public void deleteCookie(String name,HttpServletResponse response){
        Cookie newCookie = new Cookie(name, "");
        newCookie.setMaxAge(0);
        newCookie.setPath("/");
        response.addCookie(newCookie);
    }

    public String searchCookie(String name,HttpServletRequest request){
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               if(cookie.getName().equals(name)){
                    return cookie.getValue();
            
                } 
            }
        }
        return null;
    }


    
}
