package com.web3.with.service;

import com.web3.with.entity.ApplicantEntity;
import com.web3.with.entity.RoleEntity;
import com.web3.with.entity.UserEntity;
import com.web3.with.exception.http.BadRequestException;
import com.web3.with.mapper.ApplicantMapper;
import com.web3.with.mapper.EmployerMapper;
import com.web3.with.repository.ApplicantRepository;
import com.web3.with.repository.EmployerRepository;
import com.web3.with.repository.RoleRepository;
import com.web3.with.repository.UserRepository;
import com.web3.with.security.model.context.UserContext;
import com.web3.with.service.api.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.ApplicantDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    private final UserRepository userRepository;

    private final UserContext userContext;

    private final RoleRepository roleRepository;

    private final ApplicantMapper applicantMapper;


    @Override
    public void applicantRegistration(ApplicantEntity applicantEntity) throws BadRequestException {
        UserEntity user = userRepository.findByEmail(userContext.getCurrentUser().getEmail());
        if (applicantRepository.existsByEmail(applicantEntity.getEmail())){
            throw new BadRequestException("Applicant already exists");
        }else if(user.getApplicant() != null){
            throw new BadRequestException("User already has applicant");
        }else{
            applicantRepository.save(applicantEntity);
            user.setApplicant(applicantEntity);
        }
    }
}
