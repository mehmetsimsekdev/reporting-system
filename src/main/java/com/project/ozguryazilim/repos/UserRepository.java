package com.project.ozguryazilim.repos;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ozguryazilim.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUserName(String userName);
    


    
}
