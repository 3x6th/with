package com.web3.with.service;

import com.web3.with.entity.ApplicantEntity;
import com.web3.with.entity.ResumeEntity;
import com.web3.with.repository.ApplicantRepository;
import com.web3.with.repository.ResumeRepository;
import com.web3.with.security.model.context.UserContext;
import com.web3.with.service.api.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final ApplicantRepository applicantRepository;
    private final UserContext userContext;

    @Override
    public void resumeCreate(ResumeEntity resumeEntity) {
        Long userId = userContext.getCurrentUser().getId();
        ApplicantEntity applicantEntity = applicantRepository.findByUserId(userId);
        applicantEntity.setResumes(resumeEntity);
        resumeRepository.save(resumeEntity);
        applicantRepository.save(applicantEntity);
    }
}
