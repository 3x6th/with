package com.web3.with.service.api;

import org.openapitools.model.AppRs;
import org.springframework.http.ResponseEntity;

public interface ReplyService {
    ResponseEntity<AppRs> decideToReplyByApplicantId(Long employerId, Long applicantId, String answer);
}
