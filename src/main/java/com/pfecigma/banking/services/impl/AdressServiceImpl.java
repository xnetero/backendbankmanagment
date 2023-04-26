package com.pfecigma.banking.services.impl;

import com.pfecigma.banking.dto.AccountDto;
import com.pfecigma.banking.dto.AdressDto;
import com.pfecigma.banking.models.Account;
import com.pfecigma.banking.models.Addresse;
import com.pfecigma.banking.repositories.AdressRepository;
import com.pfecigma.banking.services.AdressService;
import com.pfecigma.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdressServiceImpl implements AdressService {


    private final AdressRepository repository;
    private  final ObjectsValidator<AdressDto> validator;

    @Override
    public Integer save(AdressDto dto) {

        validator.validate(dto);
        return repository.save(AdressDto.toEntity(dto)).getId();





    }

    @Override
    public List<AdressDto> findAll() {

        return repository.findAll().stream()
                .map(AdressDto::fromEntity)//This method converts the entity to a DTO object.
                .collect(Collectors.toList());

    }

    @Override
    public AdressDto findById(Integer id) {

        return repository.findById(id)
                .map(AdressDto::fromEntity)
                .orElseThrow( ()->new EntityNotFoundException("adresse not found"));
    }

    @Override
    public void delete(Integer id) {

         repository.deleteById(id);

    }
}
