package com.pfecigma.banking.controllers;

import com.pfecigma.banking.dto.AdressDto;
import com.pfecigma.banking.services.AdressService;
import com.pfecigma.banking.services.UserService;
import lombok.RequiredArgsConstructor;import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AddressController {

    private final AdressService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AdressDto addressDto
    ) {
        return ResponseEntity.ok(service.save(addressDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AdressDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AdressDto> findById(
            @PathVariable("address-id") Integer addressId
    ) {
        return ResponseEntity.ok(service.findById(addressId));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("address-id") Integer addressId
    ) {
        service.delete(addressId);
        return ResponseEntity.accepted().build();
    }

}
