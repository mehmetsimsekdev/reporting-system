package com.project.ozguryazilim.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.URLEncoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.ozguryazilim.entities.RefreshToken;
import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.requests.RefreshRequest;
import com.project.ozguryazilim.requests.UserRequest;
import com.project.ozguryazilim.responses.AuthResponse;
import com.project.ozguryazilim.security.JwtTokenProvider;
import com.project.ozguryazilim.services.CookieService;
import com.project.ozguryazilim.services.RefreshTokenService;
import com.project.ozguryazilim.services.UserService;


@RestController
@RequestMapping
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RefreshTokenService refreshTokenService;
	private CookieService cookieService;
    
    public AuthController(AuthenticationManager authenticationManager, UserService userService, 
    		PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService,CookieService cookieService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
		this.cookieService=cookieService;
    }
    @PostMapping("/")
    public void login(@ModelAttribute("signinRequest") UserRequest loginRequest,HttpServletResponse response,HttpServletRequest request) throws IOException{
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user = userService.getOneUserByUserName(loginRequest.getUserName());

		cookieService.addCookie("id", Long.toString(user.getId()), 60*60, response);
		cookieService.addCookie("access_token", java.net.URLEncoder.encode(jwtToken, "UTF-8"), 60*60, response);
		cookieService.addCookie("isLogin", "true", 60*60, response);
		
		response.sendRedirect("/reports");

		
    }
    
    @PostMapping("/register")
	public void register(@ModelAttribute("signupRequest") UserRequest signupRequest,RedirectAttributes ra,
	HttpServletResponse response,HttpServletRequest request)throws IOException {
		AuthResponse authResponse = new AuthResponse();
		if(userService.getOneUserByUserName(signupRequest.getUserName()) != null) {
			authResponse.setMessage("Username already in use.");
			response.sendRedirect("/register?error=username+is+already+in+use");
		}
		
		User user = new User();
		user.setUserName(signupRequest.getUserName()); // TODO add token and id cookies and send to response
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setName(signupRequest.getName());
		userService.saveOneUser(user);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(signupRequest.getUserName(), signupRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);

		cookieService.addCookie("id", Long.toString(user.getId()), 60*60, response);
		cookieService.addCookie("access_token", java.net.URLEncoder.encode(jwtToken, "UTF-8"), 60*60, response);
		cookieService.addCookie("isLogin", "true", 60*60, response);

		response.sendRedirect("/reports");
	}
	@GetMapping("/signout")
	public ResponseEntity<String> logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				cookieService.deleteCookie(cookie.getName(), resp);
			}
		resp.sendRedirect("/");


		return new ResponseEntity<>("Successfully logout", HttpStatus.OK);		
	}


    /*@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
		AuthResponse response = new AuthResponse();
		RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
		if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
				!refreshTokenService.isRefreshExpired(token)) {

			User user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
			response.setMessage("token successfully refreshed.");
			response.setAccessToken("Bearer " + jwtToken);
			response.setUserId(user.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);		
		} else {
			response.setMessage("refresh token is not valid.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
	}*/
    
}
