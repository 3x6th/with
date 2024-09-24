package com.web3.with.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "resume")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"applicantz", "tagList"})
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinTable(name = "applicant_resume", joinColumns = {@JoinColumn(name = "resume_id")}, inverseJoinColumns = {@JoinColumn(name = "applicant_id")})
    private ApplicantEntity applicant;

    @Column(name = "desired_salary")
    private String desiredSalary;

    @Column(name = "work_mode")
    private String workMode;

    @Column(name = "location")
    private String location;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "resume_tag", joinColumns = {@JoinColumn(name = "resume_id")}, inverseJoinColumns = {
            @JoinColumn(name = "tag_id")
    })
    private Set<TagEntity> tagList = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vacancy_resume", joinColumns = {@JoinColumn(name = "resume_id")}, inverseJoinColumns = {@JoinColumn(name = "vacancy_id")})
    private List<VacancyEntity> vacancies;

    public void setVacancy(VacancyEntity vacancy) {
        this.vacancies.add(vacancy);
    }
}
