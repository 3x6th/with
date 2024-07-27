package com.web3.with.service;

import com.web3.with.mapper.EmployerMapper;
import com.web3.with.mapper.VacancyMapper;
import com.web3.with.repository.EmployerRepository;
import com.web3.with.service.api.EmployerService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.EmployerDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    private final EmployerMapper employerMapper;

    private final VacancyMapper vacancyMapper;

    @Transactional(readOnly = true)
    @Override
    public EmployerDTO findById(Long id) {
        return employerRepository.findById(id)
                                 .map(employer -> {
                                     EmployerDTO dto = employerMapper.entityToSimpleDto(employer);
                                     dto.setVacancies(employer.getVacancies().stream()
                                                              .map(vacancyMapper::entityToSimpleDto)
                                                              .collect(Collectors.toList()));
                                     return dto;
                                 })
                                 .orElseThrow(() -> new RuntimeException("employer not found"));
    }

}
