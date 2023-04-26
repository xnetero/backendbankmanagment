package com.pfecigma.banking.services.impl;

import com.pfecigma.banking.dto.AccountDto;
import com.pfecigma.banking.dto.UserDto;
import com.pfecigma.banking.exception.OperationNonPermitedException;
import com.pfecigma.banking.models.Account;
import jakarta.persistence.EntityNotFoundException;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import com.pfecigma.banking.repositories.AccountRepository;
import com.pfecigma.banking.services.AccountService;
import com.pfecigma.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private  final ObjectsValidator<AccountDto> validator;


    @Override
    public Integer save(AccountDto dto) {

        /// block account update
        if(dto.getId()!=null){
            throw new OperationNonPermitedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "update not permitted"
            );
        }


        validator.validate(dto);

        Account acc=AccountDto.toEntity(dto);
        boolean userhasalreadyaccount=repository.findByUserId(acc.getUser().getId()).isPresent();
        if(userhasalreadyaccount){
            throw new OperationNonPermitedException(
                    "the selected user has already an active account",
                    "create account",
                    "Accont service",
                    "account creation"
            );
        }
        //generate random iban
        acc.setIban(GenerateRandomIban());

        return repository.save(acc).getId();

    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto ::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("no account was found"));


    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);

    }


    private String GenerateRandomIban(){

        //Generate iban

        String iban = Iban.random(CountryCode.MA).toFormattedString();

        // check if the iban exists

       boolean ibanExists= repository.findByIban(iban).isPresent();

        //if exists generate new one ...else return generated iban

       if(ibanExists){
           GenerateRandomIban();
       }

        return iban;

    }
}
