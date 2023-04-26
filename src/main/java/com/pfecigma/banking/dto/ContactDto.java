package com.pfecigma.banking.dto;


import com.pfecigma.banking.models.Contact;
import com.pfecigma.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContactDto {

    private String firstname;

    private String lastname;
    private String email;

//    private String password;

    private  String iban;

    private Integer userId;


    public static ContactDto fromEntity(Contact contact){

        return ContactDto.builder()
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();



    }



    public static Contact toEntity(ContactDto contact){

        return Contact.builder()
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(User.builder().id(contact.getUserId()).build())
                .build();


    }
}
