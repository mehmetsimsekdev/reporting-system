package com.project.ozguryazilim.responses;

import lombok.Data;

@Data
public class AuthResponse {
    String message;
	Long userId;
	String accessToken;
	String refreshToken;
}
