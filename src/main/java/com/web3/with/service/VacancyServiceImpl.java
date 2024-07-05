package com.web3.with.service;

import com.web3.with.entity.VacancyEntity;
import com.web3.with.repository.VacancyRepository;
import com.web3.with.service.api.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    @Value("${vacancy.pageSize}")
    private int pageSize;

    @Override
    public VacancyEntity getVacancies() {
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<VacancyEntity> vacancy = vacancyRepository.findPage(pageable);

        return null;
    }
}
