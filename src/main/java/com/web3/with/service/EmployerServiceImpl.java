package com.web3.with.service;

import com.web3.with.entity.EmployerEntity;
import com.web3.with.entity.VacancyEntity;
import com.web3.with.mapper.VacancyMapper;
import com.web3.with.repository.EmployerRepository;
import com.web3.with.repository.VacancyRepository;
import com.web3.with.service.api.EmployerService;
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

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<EmployerEntity> findById(Long id) {
        return employerRepository.findById(id);
    }
}
