package com.web3.with.repository;

import com.web3.with.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    @Modifying
    @Query(value = "UPDATE reply " +
            "SET status_id = :newStatusId " +
            "WHERE vacancy_id = :vacancyId " +
            "AND applicant_id = :applicantId " +
            "AND EXISTS (" +
            "    SELECT 1 FROM vacancy v " +
            "    JOIN employer e ON e.id = v.employer_id " +
            "    JOIN employer_manage em ON em.employer_id = e.id " +
            "    WHERE v.id = reply.vacancy_id " +
            "    AND em.app_user_id = :employerUserId" +
            ")",
            nativeQuery = true)
    int updateReplyStatus(@Param("employerUserId") Long employerUserId,
                          @Param("vacancyId") Long vacancyId,
                          @Param("applicantId") Long applicantId,
                          @Param("newStatusId") Long newStatusId);

}
