package com.web3.with.service;

import com.web3.with.repository.ReplyRepository;
import com.web3.with.service.api.ReplyService;
import com.web3.with.service.api.UserService;
import com.web3.with.util.StatusEnum;
import lombok.AllArgsConstructor;
import org.openapitools.model.AppRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final UserService userService;
    private final ReplyRepository replyRepository;

    @Transactional
    @Override
    public ResponseEntity<AppRs> decideToReplyByApplicantId(Long VacancyId, Long applicantId, String answer) {
        Long newStatusId;

        try {
            newStatusId = getStatusIdFromAnswer(answer);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new AppRs("400", "Invalid status value", null), HttpStatus.BAD_REQUEST);
        }

        Long employerId = userService.getUserIdFromCurrentSession();

        int updatedRows = replyRepository.updateReplyStatus(employerId, VacancyId, applicantId, newStatusId);
        if (updatedRows != 1) {
            return new ResponseEntity<>(new AppRs("400", "No such reply found or you don't have permissions", null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new AppRs("200", "Status updated successfully", null), HttpStatus.OK);
    }

    private Long getStatusIdFromAnswer(String answer) {
        return StatusEnum.fromString(answer);
    }
}
