package com.pfecigma.banking.dto;


import com.pfecigma.banking.models.Transaction;
import com.pfecigma.banking.models.TransctionType;
import com.pfecigma.banking.models.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransactionDto {


    private  Integer id;


    @Positive
    @Max(value = 1000000)
    @Min(value = 10)
    private BigDecimal amount ;

    private TransctionType type;

    private String destinationIban;

    private Integer userId;

    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .destinationIban(transaction.getDestinationIban())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDto transaction){
        return Transaction.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .destinationIban(transaction.getDestinationIban())
                .user(User.builder().id(transaction.getUserId()).build())
                .build();
    }

}
