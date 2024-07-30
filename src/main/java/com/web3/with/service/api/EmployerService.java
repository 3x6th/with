package com.web3.with.service.api;

import org.openapitools.model.EmployerDTO;
import org.openapitools.model.EmployerWithVacancyRs;

public interface EmployerService {

    /**
     * Метод находит работодателя согласно переденному ID
     *
     * @param id
     *         ID работодателя
     *
     * @return {@link EmployerDTO} Если работодатель найден, вернет объект,
     * содержащий внутри себя ссылку на объект работодателя, иначе пустой объект
     */
    EmployerDTO findById(Long id);

    /**
     * Метод находит работодателя согласно переденному ID вместе с его вакансиями
     *
     * @param id
     *         ID работодателя
     *
     * @return {@link EmployerWithVacancyRs} Если работодатель найден, вернет объект,
     * содержащий внутри себя ссылку на объект работодателя с вакансиями, иначе пустой объект
     */
    EmployerWithVacancyRs findEmployerWithVacanciesById(Long id);

}
