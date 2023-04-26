package com.pfecigma.banking.services;

import com.pfecigma.banking.dto.TransactionDto;

import java.util.List;


public interface TransactionService extends  AbstractSerivce<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);
}
