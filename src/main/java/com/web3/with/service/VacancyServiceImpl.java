package com.web3.with.service;

import com.web3.with.entity.ApplicantEntity;
import com.web3.with.entity.ResumeEntity;
import com.web3.with.entity.UserEntity;
import com.web3.with.entity.VacancyEntity;
import com.web3.with.exception.http.BadRequestException;
import com.web3.with.mapper.VacancyMapper;
import com.web3.with.repository.ApplicantRepository;
import com.web3.with.repository.ResumeRepository;
import com.web3.with.repository.UserRepository;
import com.web3.with.repository.VacancyRepository;
import com.web3.with.security.model.context.UserContext;
import com.web3.with.service.api.VacancyService;
import com.web3.with.specification.VacancySpecification;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    private final VacancyMapper vacancyMapper;

    private final UserContext userContext;
    private final UserRepository userRepository;
    private final ApplicantRepository applicantRepository;
    private final ResumeRepository resumeRepository;

    @Value("${vacancy.pageSize}")
    private int pageSize;

    @Transactional
    @Override
    public VacanciesRs getVacancies(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<VacancyEntity> page = vacancyRepository.findAllWithCompanyNameAndTags(pageable);
        return vacancyMapper.pageToResponse(page, page.isLast());
    }

    @Transactional(readOnly = true)
    @Override
    public VacancyDTO findById(Long id) {
        return vacancyRepository.findById(id)
                                .map(vacancyMapper::entityToSimpleDto)
                                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public VacanciesRs getVacanciesByKeyword(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Specification<VacancyEntity> specification = VacancySpecification.searchByKeyword(keyword);
        Page<VacancyEntity> page = vacancyRepository.findAll(specification, pageable);
        return vacancyMapper.pageToResponse(page, page.isLast());
    }

    @Override
    public void applyResumeById(Long id, Long resumeId) throws BadRequestException {
        if (!vacancyRepository.existsById(id)){
            throw new BadRequestException("Vacancy not found");
        }
        ApplicantEntity applicant = applicantRepository.findByUserId(userContext.getCurrentUser().getId());
        ResumeEntity resume = applicant.getResume(resumeId);
        if (resume == null){
            throw new BadRequestException("Resume not found");
        }
        VacancyEntity vacancy = vacancyRepository.findById(id).get();
        resume.setVacancy(vacancy);
        vacancyRepository.save(vacancy);
        resumeRepository.save(resume);
    }


}
