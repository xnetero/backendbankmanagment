package com.pfecigma.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Transaction extends AbstractEntity {




    private BigDecimal amount ;


    @Enumerated(EnumType.STRING)
    private TransctionType type;

    private String destinationIban;





    // many tranaction can be done by one user
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;




}



