package com.web3.with.repository;

import com.web3.with.entity.ApplicantEntity;
import org.openapitools.model.ApplicantDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT a " +
            "FROM ApplicantEntity a " +
            "LEFT JOIN FETCH a.user u WHERE u.id = :userId")
    ApplicantEntity findByUserId(Long userId);
}
