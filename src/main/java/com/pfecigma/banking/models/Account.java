package com.pfecigma.banking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Account extends AbstractEntity {




   

    private String iban;




    //one account belong to on user
    @OneToOne
    @JoinColumn(name="id_user")
    private User user;





}
