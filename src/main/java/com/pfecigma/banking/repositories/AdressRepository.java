package com.pfecigma.banking.repositories;

import com.pfecigma.banking.models.Account;
import com.pfecigma.banking.models.Addresse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdressRepository extends JpaRepository<Addresse,Integer> {



}
