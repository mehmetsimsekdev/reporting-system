package com.project.ozguryazilim.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ozguryazilim.entities.RefreshToken;


public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Long>{

    RefreshToken findByUserId(Long userId);

}
