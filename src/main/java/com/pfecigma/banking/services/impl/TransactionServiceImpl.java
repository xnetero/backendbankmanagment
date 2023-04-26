package com.pfecigma.banking.services.impl;

import com.pfecigma.banking.dto.AdressDto;
import com.pfecigma.banking.dto.TransactionDto;
import com.pfecigma.banking.models.Transaction;
import com.pfecigma.banking.models.TransctionType;
import com.pfecigma.banking.repositories.TransactionRepository;
import com.pfecigma.banking.services.TransactionService;
import com.pfecigma.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private  final ObjectsValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {

        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);//transfer the dto to entity
        BigDecimal transactionMultiplier = BigDecimal
                .valueOf(getTransactionMultiplier(transaction.getType()));//
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return repository.save(transaction).getId();
    }




    @Override
    public List<TransactionDto> findAll() {

        return repository.findAll().stream().map(TransactionDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return  repository.findById(id).map(TransactionDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("no transcation  was found"));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        repository.deleteById(id);
    }

      private int getTransactionMultiplier(TransctionType type) {
        //if the transction type is transfer return -1 else return 1
        return TransctionType.TRANSFER == type ? -1 : 1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
