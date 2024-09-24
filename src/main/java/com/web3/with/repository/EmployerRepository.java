package com.web3.with.repository;

import com.web3.with.entity.EmployerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for employer operations.
 */
@Repository
public interface  EmployerRepository extends JpaRepository<EmployerEntity, Long> {

    @Query("SELECT e FROM EmployerEntity e LEFT JOIN FETCH e.vacancies WHERE e.id = :id")
    Optional<EmployerEntity> findByIdWithVacancies(Long id);

    boolean existsByEmail(String email);
}
