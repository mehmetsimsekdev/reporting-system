package com.project.ozguryazilim.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    Long id;
    
    @Column
    String userName;
    String password;

}