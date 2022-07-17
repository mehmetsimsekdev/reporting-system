package com.project.ozguryazilim.responses;

import com.project.ozguryazilim.entities.User;

import lombok.Data;

@Data
public class UserResponse {
    Long id;
	String userName;

	public UserResponse(User entity) {
		this.id = entity.getId();
		this.userName = entity.getUserName();
	} 
}
