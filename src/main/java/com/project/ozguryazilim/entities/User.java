package com.project.ozguryazilim.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Size(min=7,max=7)
    Long id;
    
    @Column
    String userName;
    String password;

}