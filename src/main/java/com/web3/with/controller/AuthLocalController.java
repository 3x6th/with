package com.web3.with.controller;

import com.web3.with.security.model.AuthDto;
import com.web3.with.security.model.RegistrationDto;
import com.web3.with.security.securityResponse.AppSecurityResponse;
import com.web3.with.service.api.AuthLocalService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthLocalController {

    private final AuthLocalService authService;

    @PostMapping("/register")
    public AppSecurityResponse register(
            @RequestBody RegistrationDto registrationDto
    ) throws BadRequestException {
        authService.register(registrationDto);
        return new AppSecurityResponse(HttpStatus.OK, "Registered Successfully");
    }

    @PostMapping("/auth")
    public AppSecurityResponse auth(
            @RequestBody AuthDto authDto
    ) {
        return new AppSecurityResponse(HttpStatus.OK, authService.authenticate(authDto));
    }

}
