package com.web3.with.controller;

import com.web3.with.service.api.ReplyService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ReplyApi;
import org.openapitools.model.AppRs;
import org.openapitools.model.DecideToApplicantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ReplyController implements ReplyApi {

    private final ReplyService replyService;

    @Override
    @PostAuthorize("hasRole('ROLE_EMPLOYER')")
    public ResponseEntity<AppRs> decideByVacancyId(Long id, DecideToApplicantDTO decideToApplicantDTO) throws Exception {
        return replyService.decideToReplyByApplicantId(id, decideToApplicantDTO.getApplicantId(), decideToApplicantDTO.getDecide());
    }

}