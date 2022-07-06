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
    String name; // todo: fix DB
    String surname; 

}