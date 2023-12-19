package com.tasif.enrollservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer minAge;
    private Integer maxAge;
    private Integer minAgeAtMaturity;
    private Integer maxAgeAtMaturity;
    private String minTerm;
    private String maxTerm;
    private String minSumAssured;
    private String maxSumAssured;
}
