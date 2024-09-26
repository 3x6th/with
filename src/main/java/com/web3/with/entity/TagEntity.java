package com.web3.with.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tag")
@EqualsAndHashCode(exclude = {"vacancies"})
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tagList")
    private Set<VacancyEntity> vacancies = new HashSet<>();

    @ManyToMany(mappedBy = "tagList")
    private Set<ResumeEntity> resumes = new HashSet<>();

}
