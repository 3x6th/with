package com.web3.with.controller;

import org.openapitools.api.VacanciesApi;
import org.openapitools.model.VacanciesRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class VacancyController implements VacanciesApi {

    @Override
    public ResponseEntity<VacanciesRs> getVacancies() {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
