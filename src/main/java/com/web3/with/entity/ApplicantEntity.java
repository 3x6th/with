package com.web3.with.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity for applicant.
 */
@Entity
@Table(name = "applicant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "applicant")
    private UserEntity user;

    @OneToMany
    @JoinTable(name = "applicant_resume", joinColumns = {@JoinColumn(name = "applicant_id")}, inverseJoinColumns = {@JoinColumn(name = "resume_id")})
    private List<ResumeEntity> resumes =  new ArrayList<>();

    public void setResumes(ResumeEntity resume) {
        this.resumes.add(resume);
    }

    public ResumeEntity getResume(Long id) {
        for (ResumeEntity resume : resumes) {
            if (resume.getId().equals(id)) {
                return resume;
            }
        }
        return null;
    }
}
