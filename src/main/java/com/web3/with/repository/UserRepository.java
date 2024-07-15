package com.web3.with.repository;

import com.web3.with.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT DISTINCT u " +
            "FROM UserEntity u " +
            "JOIN FETCH u.role " +
            "WHERE u.email = :email")
    Optional<UserEntity> findByEmailWithRoles(String email);

    boolean existsByEmail(String email);

}
