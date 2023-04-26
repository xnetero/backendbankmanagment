package com.pfecigma.banking.services;

import com.pfecigma.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractSerivce<ContactDto> {

    List<ContactDto> findAllByUserId(Integer userId);

}
