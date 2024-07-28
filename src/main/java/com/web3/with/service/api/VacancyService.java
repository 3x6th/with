package com.web3.with.service.api;

import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;

public interface VacancyService {

    /**
     * Метод возвращает список вакансий
     *
     * @param pageNumber
     *         Номер страницы
     *
     * @return {@link VacanciesRs} Объект, содержащий постранично список вакансий
     * и информацию является ли данная страница последней
     */
    VacanciesRs getVacancies(int pageNumber);

    /**
     * Метод находит вакансию согласно переденному ID
     *
     * @param id
     *         ID вакансии
     *
     * @return {@link VacancyDTO} Если вакансии найдена вернет объект,
     * содержащий внутри себя ссылку на объект вакансии, иначе пустой объект
     */
    VacancyDTO findById(Long id);

    VacanciesRs getVacanciesByKeyword(String keyword);

}
