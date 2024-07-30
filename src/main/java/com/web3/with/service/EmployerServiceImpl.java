package com.web3.with.service;

import com.web3.with.exception.http.NotFoundException;
import com.web3.with.mapper.EmployerMapper;
import com.web3.with.mapper.VacancyMapper;
import com.web3.with.repository.EmployerRepository;
import com.web3.with.service.api.EmployerService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.EmployerDTO;
import org.openapitools.model.EmployerWithVacancyRs;
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
                                 .map(employerMapper::entityToSimpleDto)
                                 .orElseThrow(() -> new NotFoundException("employer not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public EmployerWithVacancyRs findEmployerWithVacanciesById(Long id) {
        return employerRepository.findByIdWithVacancies(id)
                                 .map(employer -> {
                                     EmployerWithVacancyRs dto = employerMapper.entityToEmployerWithVacancies(
                                             employer);
                                     dto.setVacancies(employer.getVacancies().stream()
                                                              .map(vacancyMapper::entityToPreviewDto)
                                                              .collect(Collectors.toList()));
                                     return dto;
                                 })
                                 .orElseThrow(() -> new NotFoundException("employer not found"));
    }

}
