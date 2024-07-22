package com.web3.with.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
