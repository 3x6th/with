package com.web3.with.repository;

import com.web3.with.entity.VacancyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {

    Page<VacancyEntity> findAll(Pageable pageable);

    @Query("SELECT v FROM VacancyEntity v LEFT JOIN FETCH v.employer e LEFT JOIN FETCH v.tagList t")
    Page<VacancyEntity> findAllWithCompanyNameAndTags(Pageable pageable);

}
