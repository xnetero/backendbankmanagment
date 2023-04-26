package com.pfecigma.banking.dto;


import com.pfecigma.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {



    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank

    private String firstname;

    @NotNull
    @NotEmpty
    @NotBlank

    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank
@Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8 , max = 16)
    private String password;


    @Past
    private LocalDateTime datebirthday;



    public static UserDto fromEntity(User user){
        return UserDto.builder().
                firstname(user.getFirstname()).
                lastname(user.getLastname()).
                email(user.getEmail()).
                password(user.getPassword()).build();


    }







    public static User toEntity(UserDto user){
        return User.builder().
                firstname(user.getFirstname()).
                lastname(user.getLastname()).
                email(user.getEmail()).
                password(user.getPassword()).
                build();

    }


}
