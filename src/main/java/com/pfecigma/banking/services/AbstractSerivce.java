package com.pfecigma.banking.services;

import java.util.List;

public interface AbstractSerivce<T> {


    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);




}
