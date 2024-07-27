package com.web3.with.entity;

import com.web3.with.security.model.role.RoleName;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "role")
public @Data class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleName role;

}
