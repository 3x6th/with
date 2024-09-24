package com.web3.with.entity;

import jakarta.persistence.*;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity for employer.
 */
@Data
@Entity
@Table(name = "employer")
@EqualsAndHashCode(exclude = {"vacancies"})
public class EmployerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "website")
    private String website;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VacancyEntity> vacancies;

    @ManyToOne
    @JoinTable(name = "user_employer", joinColumns = {@JoinColumn(name = "employer_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void setVacancies(VacancyEntity vacancy) {
        this.vacancies.add(vacancy);
    }
}
