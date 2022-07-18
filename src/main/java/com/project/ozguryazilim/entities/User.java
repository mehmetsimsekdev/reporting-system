package com.project.ozguryazilim.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @SequenceGenerator(name="seq",sequenceName = "reportSeq", initialValue=1000000, allocationSize=50)
    @GeneratedValue(generator = "seq")
    Long id;
    
    @Column
    String userName;
    String password;
    String name;

}