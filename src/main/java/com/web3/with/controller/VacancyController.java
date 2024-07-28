package com.web3.with.controller;

import com.web3.with.entity.VacancyEntity;
import com.web3.with.service.api.VacancyService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.VacanciesApi;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;
import org.openapitools.model.VacancyRq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class VacancyController implements VacanciesApi {

    private final VacancyService vacancyService;

    /**
     * POST /vacancies : Обрабатывает запрос на отображение всех вакансий
     *
     * @param vacancyRq
     *         Объект для передачи номера страницы (required)
     * @param contentType
     *         Объект для передачи описания типа передаваемых данных (optional)
     *
     * @return {@link VacanciesRs} Список вакансий
     */
    @Override
    public ResponseEntity<VacanciesRs> getVacancies(VacancyRq vacancyRq, String contentType) {
        return ResponseEntity.ok(vacancyService.getVacancies(vacancyRq.getPage()));
    }

    /**
     * GET /vacancy/{id} : Получение вакансии по ID
     *
     * @param id
     *         ID вакансии для получения информации (required)
     *
     * @return {@link VacancyDTO} Информация о вакансии с определенным ID
     */
    @Override
    public ResponseEntity<VacancyDTO> getVacancyById(Long id) {
        return ResponseEntity.ok(vacancyService.findById(id));
    }

    /**
     * POST /vacancies/search : Получение вакансий по ключевым словам <p>
     * Поиск ведется по полям {@linkplain VacancyEntity#title} и {@link VacancyEntity#description}
     *
     * @param vacancyRq
     *         Объект для передачи номера страницы (required)
     * @param keyword
     *         Ключевые слова для поиска вакансий  (optional)
     *
     * @return {@link VacanciesRs} Список вакансий
     */
    @Override
    public ResponseEntity<VacanciesRs> getVacanciesByKeyword(VacancyRq vacancyRq, String keyword) {
        return ResponseEntity.ok(vacancyService.getVacanciesByKeyword(vacancyRq.getPage(), keyword));
    }

}
