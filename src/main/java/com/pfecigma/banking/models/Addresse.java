package com.pfecigma.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Addresse extends AbstractEntity{



    private String street;
    private Integer housenum;
    private  Integer zipcode;
    private  String city;
    private String country;

    @OneToOne
    @JoinColumn(name="id_user")
    private User user;


}
