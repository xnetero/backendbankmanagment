package com.pfecigma.banking.services.impl;

import com.pfecigma.banking.dto.AccountDto;
import com.pfecigma.banking.dto.UserDto;
import com.pfecigma.banking.models.Account;
import com.pfecigma.banking.models.User;
import com.pfecigma.banking.repositories.UserRepository;
import com.pfecigma.banking.services.AccountService;
import com.pfecigma.banking.services.UserService;
import com.pfecigma.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository repository;
    private final AccountService accountService;
    private  final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);

        User user=UserDto.toEntity(dto);
        return repository.save(user).getId();


    }

    @Override
    public List<UserDto> findAll() {

        return repository.findAll().stream()
                .map(u -> UserDto.fromEntity(u))
                .collect(Collectors.toList());



    }

    @Override
    public UserDto findById(Integer id) {


        return repository.findById(id) // Find user by ID using the repository
                .map(UserDto::fromEntity)  // Map User entity to UserDto using a static method reference
                .orElseThrow( ()->new EntityNotFoundException("no user was found")); // Throw exception if user not found


    }

    @Override
    public void delete(Integer id) {

         repository.deleteById(id);
    }


    @Override
    public Integer validateAccount(Integer id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        if (user.getAccount() == null) {
            // create a bank account
            AccountDto account = AccountDto.builder()
                    .user(UserDto.fromEntity(user))
                    .build();
            var savedAccount = accountService.save(account);
            user.setAccount(
                    Account.builder()
                            .id(savedAccount)
                            .build()
            );
        }

        user.setActive(true);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {


        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        user.setActive(false);
        repository.save(user);
        return user.getId();

    }
}
