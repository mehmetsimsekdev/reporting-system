package com.project.ozguryazilim.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.ozguryazilim.entities.Role;
import com.project.ozguryazilim.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class JwtUserDetails implements UserDetails{

    static String ROLE_PREFIX = "ROLE_";

    public Long id;
    private String username;
    private String password;
    private static Role role;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities,Role role){
        this.id = id;
        this.username=username;
        this.password=password;
        this.authorities=authorities;
        this.role=role;
    }

    public static JwtUserDetails create(User user){
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority(ROLE_PREFIX+role));
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), authoritiesList,user.getRole());
    }
    
    
    

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
    
}
