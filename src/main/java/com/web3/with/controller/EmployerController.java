package com.web3.with.controller;

import com.web3.with.entity.EmployerEntity;
import com.web3.with.service.api.EmployerService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.EmployerApi;
import org.openapitools.model.EmployerDTO;
import org.openapitools.model.EmployerWithVacancyRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class EmployerController implements EmployerApi {

    private final EmployerService employerService;

    /**
     * GET /employer/{id} : Получение вакансии по ID
     *
     * @param id
     *         ID работодателя для получения информации (required)
     *
     * @return {@link EmployerEntity} Информация о работодателе с определенным ID
     */
    @Override
    public ResponseEntity<EmployerDTO> getEmployerById(Long id) {
        return ResponseEntity.ok(employerService.findById(id));
    }

    /**
     * GET /vacancy/employer/{id} : Получение работодателя по ID вместе с вакансиями
     *
     * @param id
     *         ID работодателя для получения информации (required)
     *
     * @return {@link EmployerEntity} Информация о работодателе с определенным ID
     */
    @Override
    public ResponseEntity<EmployerWithVacancyRs> getEmployerWithVacanciesById(Long id) {
        return ResponseEntity.ok(employerService.findEmployerWithVacanciesById(id));
    }

}
