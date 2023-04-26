package com.pfecigma.banking.services;

import com.pfecigma.banking.dto.UserDto;

public interface UserService extends AbstractSerivce<UserDto>{

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);


}
