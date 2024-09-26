package com.web3.with.repository;

import com.web3.with.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for user operations.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT DISTINCT u " +
            "FROM UserEntity u " +
            "JOIN FETCH u.role " +
            "WHERE u.email = :email")
    Optional<UserEntity> findByEmailWithRoles(String email);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    @Query("SELECT u " +
            "FROM UserEntity u " +
            "JOIN FETCH u.role " +
            "WHERE u.email = :identifier OR u.login = :identifier")
    Optional<UserEntity> findByEmailOrLogin(@Param("identifier") String identifier);

    UserEntity findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 " +
            "THEN TRUE ELSE FALSE END " +
            "FROM UserEntity u " +
            "WHERE u.email = :identifier OR u.login = :identifier")
    boolean existsByEmailOrLogin(@Param("identifier") String identifier);

    UserEntity findById(long id);
}
