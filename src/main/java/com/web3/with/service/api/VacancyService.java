package com.web3.with.service.api;

import com.web3.with.entity.VacancyEntity;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;

import java.util.Optional;

public interface VacancyService {

    /**
     * Метод возвращает список вакансий
     *
     * @param pageNumber Номер страницы
     * @return Объект, содержащий постранично список вакансий и информацию является ли данная страница последней
     */
    VacanciesRs getVacancies(int pageNumber);

    /**
     * Метод находит вакансию согласно переденному ID
     *
     * @param id ID вакансии
     * @return Если вакансии найдена вернет объект, содержащий внутри себя ссылку на объект вакансии, иначе пустой объект
     */
    Optional<VacancyDTO> findById(Long id);
}
