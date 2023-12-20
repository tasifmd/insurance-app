package com.tasif.enrollservice.model;

import lombok.Data;

@Data
public class Policy {
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
