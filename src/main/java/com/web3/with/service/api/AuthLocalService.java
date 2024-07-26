package com.web3.with.service.api;

import com.web3.with.security.model.AuthDto;
import com.web3.with.security.model.RegistrationDto;
import org.apache.coyote.BadRequestException;

/**
 * Service for authentication.
 */
public interface AuthLocalService {

    /**
     * Register a new user.
     *
     * @param registrationDto
     *         {@link RegistrationDto} Registration data
     *
     * @throws BadRequestException
     *         If user already exists or role not found
     */
    void register(RegistrationDto registrationDto) throws BadRequestException;

    /**
     * Authenticate user.
     *
     * @param authDto
     *         {@link AuthDto} Authentication data
     *
     * @return {@link String} JWT token
     */
    String authenticate(AuthDto authDto);

}
