package com.web3.with.service.api;

import com.web3.with.entity.EmployerEntity;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;

import java.util.Optional;

public interface EmployerService {

    /**
     * Метод находит работодателя согласно переденному ID
     *
     * @param id ID работодателя
     * @return {@link com.web3.with.entity.EmployerEntity} Если работодатель найден, вернет объект,
     * содержащий внутри себя ссылку на объект работодателя, иначе пустой объект
     */
    Optional<EmployerEntity> findById(Long id);
}
