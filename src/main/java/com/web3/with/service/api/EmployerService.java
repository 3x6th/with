package com.web3.with.service.api;

import org.openapitools.model.EmployerDTO;

public interface EmployerService {

    /**
     * Метод находит работодателя согласно переденному ID
     *
     * @param id
     *         ID работодателя
     *
     * @return {@link com.web3.with.entity.EmployerEntity} Если работодатель найден, вернет объект,
     * содержащий внутри себя ссылку на объект работодателя, иначе пустой объект
     */
    EmployerDTO findById(Long id);

}
