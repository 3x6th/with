package com.web3.with.controller;

import com.web3.with.service.api.EmployerService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.EmployerApi;
import org.openapitools.model.EmployerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployerController implements EmployerApi {

    private final EmployerService employerService;

    /**
     * GET /employer/{id} : Получение вакансии по ID
     *
     * @param id ID работодателя для получения информации (required)
     * @return {@link com.web3.with.entity.EmployerEntity} Информация о работодателе с определенным ID
     */
    @Override
    public ResponseEntity<EmployerDTO> getEmployerById(Long id) {
        return ResponseEntity.ok(employerService.findById(id));
    }

}
