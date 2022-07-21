package com.project.ozguryazilim.requests;

import com.project.ozguryazilim.entities.RefreshToken;

import lombok.Data;

@Data
public class RefreshRequest {
    
  
    
    public RefreshRequest(Long id, String token) {
        this.userId=id;
        this.refreshToken=token;
    }
    Long userId;
    String refreshToken;
    
}
