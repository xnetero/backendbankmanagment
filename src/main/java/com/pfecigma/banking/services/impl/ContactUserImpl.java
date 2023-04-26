package com.pfecigma.banking.services.impl;

import com.pfecigma.banking.dto.AdressDto;
import com.pfecigma.banking.dto.ContactDto;
import com.pfecigma.banking.repositories.ContactRepository;
import com.pfecigma.banking.services.ContactService;
import com.pfecigma.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContactUserImpl implements ContactService {





    private ContactRepository repository;
    private  final ObjectsValidator<AdressDto> validator;

    @Override
    public Integer save(ContactDto dto) {

        return  repository.save(ContactDto.toEntity(dto)).getId();
    }

    @Override
    public List<ContactDto> findAll() {

        return repository.findAll().stream().map(ContactDto::fromEntity).collect(Collectors.toList());

    }

    @Override
    public ContactDto findById(Integer id) {
      return repository.findById(id).map(ContactDto::fromEntity).
              orElseThrow(()->new EntityNotFoundException("no user was found"));
    }

    @Override
    public void delete(Integer id) {


        repository.deleteById(id);

    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
