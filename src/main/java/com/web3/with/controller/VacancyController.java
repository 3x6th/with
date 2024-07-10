package com.web3.with.controller;

import com.web3.with.service.api.VacancyService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.VacanciesApi;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;
import org.openapitools.model.VacancyRq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class VacancyController implements VacanciesApi {

    private final VacancyService vacancyService;

    @Override
    public ResponseEntity<VacanciesRs> getVacancies(String contentType, VacancyRq vacancyRq) {
        return new ResponseEntity<>(vacancyService.getVacancies(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VacancyDTO> getVacancyById(Long id) {
        return VacanciesApi.super.getVacancyById(id);
    }
}
