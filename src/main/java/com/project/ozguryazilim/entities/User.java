package com.project.ozguryazilim.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    Long id;
    
    String userName;
    String password;
    public Object getUserName() {
        return null;
    }
    public Object getPassword() {
        return null;
    }
    public void setUserName(Object userName2) {
    }
    public void setPassword(Object password2) {
    }
}