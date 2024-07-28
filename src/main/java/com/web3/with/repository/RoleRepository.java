package com.web3.with.repository;

import com.web3.with.entity.RoleEntity;
import org.openapitools.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for role operations.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(RoleName role);
}
