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

public class Role extends AbstractEntity {



    private String name;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;


}
