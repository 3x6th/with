package com.web3.with.service.api;

import com.web3.with.entity.ApplicantEntity;
import com.web3.with.exception.http.BadRequestException;
import org.openapitools.model.ApplicantDto;

public interface ApplicantService {
    void applicantRegistration(ApplicantEntity applicantEntity) throws BadRequestException;
}
