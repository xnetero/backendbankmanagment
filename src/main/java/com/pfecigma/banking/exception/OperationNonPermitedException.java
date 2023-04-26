package com.pfecigma.banking.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNonPermitedException extends RuntimeException{


    private  final String errorMsg;


    private final String operationId;

    private final String source;


    private final String dependency;






}
