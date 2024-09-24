package com.web3.with.controller;

import com.web3.with.entity.ResumeEntity;
import com.web3.with.mapper.ResumeMapper;
import com.web3.with.service.api.ResumeService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ResumeApi;
import org.openapitools.model.AppSecurityRs;
import org.openapitools.model.ResumeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ResumeController implements ResumeApi {

    private final ResumeService resumeService;

    private final ResumeMapper resumeMapper;

    @Override
    public ResponseEntity<AppSecurityRs> resumeCreate(ResumeDto resumeDto) throws Exception {
        ResumeEntity resumeEntity = resumeMapper.simpleDtoToEntity(resumeDto);
        resumeService.resumeCreate(resumeEntity);
        return ResponseEntity.ok(
                new AppSecurityRs(
                        HttpStatus.OK.toString(),
                        "Resume created",
                        ZonedDateTime.now()
                )
        );
    }
}
