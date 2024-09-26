package com.web3.with.controller;

import com.web3.with.entity.ApplicantEntity;
import com.web3.with.mapper.ApplicantMapper;
import com.web3.with.service.api.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ApplicantApi;
import org.openapitools.model.AppSecurityRs;
import org.openapitools.model.ApplicantDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ApplicantController implements ApplicantApi {

    private final ApplicantService applicantService;

    private final ApplicantMapper applicantMapper;

    /**
     * POST /private/applicant/registration : Локальная регистрация апликанта
     * Локальная регистрация апликанта
     *
     * @param applicantDto  (required)
     * @return Registration success (status code 200)
     *         or Invalid input (status code 400)
     *         or Ошибка с сервера (status code 500)
     */
    @Override
    public ResponseEntity<AppSecurityRs> applicantRegistration(ApplicantDto applicantDto) throws Exception {
        ApplicantEntity applicantEntity = applicantMapper.simpleDtoToEntity(applicantDto);
        applicantService.applicantRegistration(applicantEntity);
        return ResponseEntity.ok(
                new AppSecurityRs(
                        HttpStatus.OK.toString(),
                        "Registered Successfully",
                        ZonedDateTime.now()
                )
        );
    }
}
