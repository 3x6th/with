package com.web3.with.controller;

import com.web3.with.entity.EmployerEntity;
import com.web3.with.service.api.EmployerService;
import com.web3.with.service.api.VacancyService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.VacanciesApi;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;
import org.openapitools.model.VacancyRq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/employer")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    /**
     * GET /employer/{id} : Получение вакансии по ID
     *
     * @param id ID работодателя для получения информации (required)
     * @return {@link com.web3.with.entity.EmployerEntity} Информация о работодателе с определенным ID
     */

    @GetMapping("/{id}")
    public ResponseEntity<EmployerEntity> getEmployerById(@PathVariable Long id) {
        return new ResponseEntity<>(employerService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }
}
