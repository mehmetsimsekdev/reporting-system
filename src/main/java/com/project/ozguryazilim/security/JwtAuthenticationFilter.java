package com.project.ozguryazilim.security;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.ozguryazilim.services.UserDetailsServiceImpl;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import javassist.bytecode.stackmap.TypeData.ClassName;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;
  
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );

        try{
            
            Cookie cookie = null;
            Cookie[] cookies = null;
            cookies = request.getCookies();
            String jwtToken = null;
            if( cookies != null ) {
                for (int i = 0; i < cookies.length; i++) {
                   cookie = cookies[i];
                   if(cookie.getName().equals("access_token")){
                        break;
                
                    } 
                }
                jwtToken= java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
            }else {
                Cookie id = new Cookie("id", null);
      	        Cookie accessToken = new Cookie("access_token", null);
                id.setMaxAge(60*60*24);
                accessToken.setMaxAge(60*60*24);
                response.addCookie( id );
                response.addCookie( accessToken );
                LOGGER.info("no cooikes");

             }
    

            
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)){
                Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user = userDetailsService.loadUserById(id);
                System.out.println("hereeeeeeeeeeeeeeee");
                if(user != null){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);

                }
            }
        } catch(Exception e){
            LOGGER.info("exception e");
            response.sendError(HttpServletResponse.SC_GATEWAY_TIMEOUT);
            return ;
        } 
        filterChain.doFilter(request, response);
        
    }

    
        
}
