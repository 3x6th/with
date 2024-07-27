package com.web3.with.repository;

import com.web3.with.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN FETCH u.roles " +
            "WHERE u.email = :email")
    Optional<User> findByEmailWithRoles(String email);

    boolean existsByEmail(String email);
}
