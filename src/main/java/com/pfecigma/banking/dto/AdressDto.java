package com.pfecigma.banking.dto;

import com.pfecigma.banking.models.Addresse;

import com.pfecigma.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class AdressDto {



    private Integer id;

    private String street;
    private Integer housenum;
    private  Integer zipcode;
    private  String city;
    private String country;

    private Integer userId;



    public static AdressDto fromEntity(Addresse addresse){

        return AdressDto.builder().
                id(addresse.getId()).
                street(addresse.getStreet()).
                housenum(addresse.getHousenum()).
                city(addresse.getCity()).
                country(addresse.getCountry()).
                userId(addresse.getUser().getId()).
                build();
    }


    public static Addresse toEntity(AdressDto addresse){

        return Addresse.builder().
                id(addresse.getId()).
                street(addresse.getStreet()).
                housenum(addresse.getHousenum()).
                city(addresse.getCity()).
                country(addresse.getCountry()).
                user(User.builder().id(addresse.getUserId()).build()).
                build();
    }
}
