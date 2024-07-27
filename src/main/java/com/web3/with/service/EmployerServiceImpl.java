package com.web3.with.service;

import com.web3.with.exception.http.NotFoundException;
import com.web3.with.mapper.EmployerMapper;
import com.web3.with.repository.EmployerRepository;
import com.web3.with.service.api.EmployerService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.EmployerDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    private final EmployerMapper employerMapper;

    @Transactional(readOnly = true)
    @Override
    public EmployerDTO findById(Long id) {
        return employerRepository.findById(id)
                                 .map(employerMapper::entityToSimpleDto)
                                 .orElseThrow(() -> new NotFoundException("Vacancy not found"));
    }

}
