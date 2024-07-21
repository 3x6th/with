package com.web3.with.service;

import com.web3.with.entity.VacancyEntity;
import com.web3.with.mapper.VacancyMapper;
import com.web3.with.repository.VacancyRepository;
import com.web3.with.service.api.VacancyService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.VacanciesRs;
import org.openapitools.model.VacancyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    private final VacancyMapper vacancyMapper;

    @Value("${vacancy.pageSize}")
    private int pageSize;


    @Override
    @Transactional
    public VacanciesRs getVacancies(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<VacancyEntity> page = vacancyRepository.findAllWithCompanyNameAndTags(pageable);
        return vacancyMapper.pageToResponse(page, page.isLast());
    }

    @Override
    public VacancyDTO findById(Long id) {
        return vacancyRepository.findById(id)
                .map(vacancyMapper::entityToSimpleDto)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
    }
}
