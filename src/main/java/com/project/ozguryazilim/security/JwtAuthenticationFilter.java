package com.project.ozguryazilim.security;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.ozguryazilim.requests.RefreshRequest;
import com.project.ozguryazilim.responses.AuthResponse;
import com.project.ozguryazilim.services.CookieService;
import com.project.ozguryazilim.services.RefreshTokenService;
import com.project.ozguryazilim.services.UserDetailsServiceImpl;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import javassist.bytecode.stackmap.TypeData.ClassName;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    CookieService cookieService;
  
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );

        try{
            String jwtToken =null;
            
            if( cookieService.searchCookie("access_token", request) != null ) {
                
                jwtToken= java.net.URLDecoder.decode(cookieService.searchCookie("access_token", request), "UTF-8");
            }else {
                cookieService.addCookie("id", null, 60*60, response);
                cookieService.addCookie("access_token", null, 60*60, response);
                LOGGER.info("no cooikes");

             }
    

            
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)){                
                Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user = userDetailsService.loadUserById(id);

                RefreshRequest refreshRequest= new RefreshRequest(id, refreshTokenService.getByUser(id).getToken());
                ResponseEntity<AuthResponse> authResponse = refreshTokenService.refresh(refreshRequest);
               
                String newToken =authResponse.getBody().getAccessToken();
                cookieService.addCookie("access_token", java.net.URLEncoder.encode(newToken, "UTF-8"), 60*60, response); 

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