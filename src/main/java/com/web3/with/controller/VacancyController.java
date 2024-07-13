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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class VacancyController implements VacanciesApi {

    private final VacancyService vacancyService;

    /**
     * POST /vacancies : Обрабатывает запрос на отображение всех вакансий
     *
     * @param vacancyRq Объект для передачи номера страницы (required)
     * @param contentType Объект для передачи описания типа передаваемых данных (optional)
     * @return {@link VacanciesRs} Список вакансий
     */

    @Override
    public ResponseEntity<VacanciesRs> getVacancies(VacancyRq vacancyRq, String contentType) {
        return new ResponseEntity<>(vacancyService.getVacancies(vacancyRq.getPage()), HttpStatus.OK);
    }

    /**
     * GET /vacancy/{id} : Получение вакансии по ID
     *
     * @param id ID вакансии для получения информации (required)
     * @return {@link VacancyDTO} Информация о вакансии с определенным ID
     */

    @Override
    public ResponseEntity<VacancyDTO> getVacancyById(Long id) {
        return new ResponseEntity<>(vacancyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }
}
