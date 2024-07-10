package com.web3.with.service.api;

import com.web3.with.entity.VacancyEntity;
import org.openapitools.model.VacanciesRs;

public interface VacancyService {

    VacanciesRs getVacancies(int pageNumber);
}
