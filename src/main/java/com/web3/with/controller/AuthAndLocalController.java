package com.web3.with.controller;

import com.web3.with.service.api.AuthLocalService;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.openapitools.api.AuthAndRegistrationLocalApi;
import org.openapitools.model.AppSecurityRs;
import org.openapitools.model.AuthDto;
import org.openapitools.model.RegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthAndLocalController implements AuthAndRegistrationLocalApi {

    private final AuthLocalService authService;

    /**
     * POST /auth : Handles the authentication request.
     *
     * @param authDto
     *         The data transfer object for authentication (required).
     *
     * @return {@link AppSecurityRs} The data transfer object for authentication response.
     */
    @Override
    public ResponseEntity<AppSecurityRs> auth(AuthDto authDto) {
        return ResponseEntity.ok(
                new AppSecurityRs(
                        HttpStatus.OK.toString(),
                        authService.authenticate(authDto),
                        ZonedDateTime.now()
                )
        );
    }

    /**
     * POST /registration : Handles the registration request.
     *
     * @param registrationDto
     *         The data transfer object for registration (required).
     *
     * @return {@link AppSecurityRs} The data transfer object for registration response.
     *
     * @throws BadRequestException
     *         if the request is invalid.
     */
    @Override
    public ResponseEntity<AppSecurityRs> register(RegistrationDto registrationDto)
            throws BadRequestException {
        authService.register(registrationDto);
        return ResponseEntity.ok(
                new AppSecurityRs(
                        HttpStatus.OK.toString(),
                        "Registered Successfully",
                        ZonedDateTime.now()
                )
        );
    }

}
