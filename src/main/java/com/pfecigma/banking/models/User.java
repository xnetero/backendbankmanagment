package com.pfecigma.banking.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="_user")

public class User extends  AbstractEntity {



    private String firstname;


    private String lastname;
    private String email;



    private String password;



    private  boolean active;

    @OneToOne
    private Addresse addresse;

    //one user can make many transaction
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;


    // user can have one account
    @OneToOne
    private  Account account;


    @OneToOne
    private Role role;



}
