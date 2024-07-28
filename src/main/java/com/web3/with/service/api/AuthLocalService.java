package com.web3.with.service.api;

import org.apache.coyote.BadRequestException;
import org.openapitools.model.AuthDto;
import org.openapitools.model.RegistrationDto;

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
     *         If a user already exists or role not found
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
