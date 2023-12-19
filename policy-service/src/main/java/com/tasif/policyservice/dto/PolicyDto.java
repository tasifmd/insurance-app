package com.tasif.policyservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class PolicyDto {
    private String name;
    private Integer minAge;
    private Integer maxAge;
    private Integer minAgeAtMaturity;
    private Integer maxAgeAtMaturity;
    private List<String> minTerm;
    private String maxTerm;
    private String minSumAssured;
    private String maxSumAssured;
}
