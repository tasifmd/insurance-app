package com.tasif.policyservice.service;

import com.tasif.policyservice.dto.PolicyDto;
import com.tasif.policyservice.model.Policy;

import java.util.List;

public interface PolicyService {
    public Policy createPolicy(PolicyDto policyDto);
    public Policy updatePolicy(Integer id, PolicyDto policyDto);
    public Policy deletePolicy(Integer id);

    public List<Policy> getAllPolicy();

    public Policy getPolicy(Integer id);

}
