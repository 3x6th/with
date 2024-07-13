package com.web3.with.service.api;

import com.web3.with.security.model.AuthDto;
import com.web3.with.security.model.RegistrationDto;
import org.apache.coyote.BadRequestException;

public interface AuthLocalService {

    void register(RegistrationDto registrationDto) throws BadRequestException;
    String authenticate(AuthDto authDto);
}
