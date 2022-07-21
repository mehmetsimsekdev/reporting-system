package com.project.ozguryazilim.services;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.ozguryazilim.entities.RefreshToken;
import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.repos.RefreshTokenRepository;
import com.project.ozguryazilim.requests.RefreshRequest;
import com.project.ozguryazilim.responses.AuthResponse;
import com.project.ozguryazilim.security.JwtTokenProvider;

@Service
public class RefreshTokenService {
    
    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;
	private JwtTokenProvider jwtTokenProvider;

    
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,JwtTokenProvider jwtTokenProvider){
        this.refreshTokenRepository=refreshTokenRepository;
		this.jwtTokenProvider = jwtTokenProvider;

    }
    public boolean isRefreshExpired(RefreshToken token){
        return token.getExpiryDate().before(new Date());
    }

    public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
		if(token == null) {
			token =	new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}
    
    
    public RefreshToken getByUser(Long userId) {
		return refreshTokenRepository.findByUserId(userId);	
	}
	
	
	public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
		AuthResponse response = new AuthResponse();
		RefreshToken token = getByUser(refreshRequest.getUserId());
		if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
				!isRefreshExpired(token)) {

			User user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
			response.setMessage("token successfully refreshed.");
			response.setAccessToken(jwtToken);
			response.setUserId(user.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);		
		} else {
			response.setMessage("refresh token is not valid.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
	}

}
