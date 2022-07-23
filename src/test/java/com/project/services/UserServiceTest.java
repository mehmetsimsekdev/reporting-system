package com.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.ozguryazilim.entities.Role;
import com.project.ozguryazilim.entities.User;
import com.project.ozguryazilim.repos.UserRepository;
import com.project.ozguryazilim.security.JwtTokenProvider;
import com.project.ozguryazilim.services.RefreshTokenService;
import com.project.ozguryazilim.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    UserRepository MockuserRepository;
    @Mock
    AuthenticationManager MockauthenticationManager;
    @Mock
    JwtTokenProvider MockjwtTokenProvider;
    @Mock
    PasswordEncoder MockpasswordEncoder;
    @Mock
    RefreshTokenService MockrefreshTokenService;

    @InjectMocks
    UserService UserServiceUnderTest;

    @Test
    void getAllUsers_ShouldGetAll(){
        User user = new User();
        user.setId((long) 23131231);
        user.setUserName("assasfdsfvds");
        List<User> userList = Collections.singletonList(user);

        when(MockuserRepository.findAll()).thenReturn(userList);


        List<User> actual = UserServiceUnderTest.getAllUsers();

        assertEquals(user.getId(), actual.get(0).getId());
        assertEquals(user.getUserName(), actual.get(0).getUserName());    }



    @Test
    void deleteById_itShouldDeleteFromRepo(){
        // given
        when(MockuserRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        UserServiceUnderTest.deleteById(1L);

        // then
        assertEquals(Optional.empty(),MockuserRepository.findById(1L));
    }
}


