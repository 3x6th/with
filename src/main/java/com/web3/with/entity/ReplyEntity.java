package com.web3.with.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "vacancy_id")
    private Long vacancy_id;

    @Column(name = "status_id")
    private Long status_id;
}
