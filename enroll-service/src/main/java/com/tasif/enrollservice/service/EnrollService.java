package com.tasif.enrollservice.service;

import com.tasif.enrollservice.dto.EnrollDto;
import com.tasif.enrollservice.model.Enroll;

public interface EnrollService {
    public Enroll enrollUser(String username, Integer policyId, EnrollDto enrollDto);
    public Enroll claim(String username, Integer policyId, Integer claimAmount);
}
