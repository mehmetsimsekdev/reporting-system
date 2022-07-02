package com.project.ozguryazilim.entities;

import javax.persistence.Column;
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
    @Column
    String userName;
    String password;
  /*  public Object getUserName() {
        return userName;
    }
    public Object getPassword() {
        return password;
    }
    public Object setUserName(Object userName2) {
        return userName2;
    }
    public Object setPassword(Object password2) {
        return password2;
    }*/
}