package com.web3.with.entity;

import com.web3.with.security.model.auth.AuthProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Main entity of user.
 */
@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false)
    private AuthProvider authProvider;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_applicant", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "applicant_id")
    })
    private ApplicantEntity applicant;

    @OneToMany
    @JoinTable(name = "user_employer", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "employer_id")})
    private Set<EmployerEntity> employers = new HashSet<>();

    public void setEmployers(EmployerEntity employer) {
        this.employers.add(employer);
    }
}
