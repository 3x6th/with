package com.web3.with.service.api;

import com.web3.with.entity.ResumeEntity;
import org.springframework.stereotype.Service;

public interface ResumeService {
    void resumeCreate(ResumeEntity resumeEntity);
}
