package com.tasif.policyservice.service;

import com.tasif.policyservice.dto.PolicyDto;
import com.tasif.policyservice.exception.PolicyAlreadyExistsException;
import com.tasif.policyservice.exception.PolicyNotFoundException;
import com.tasif.policyservice.model.Policy;
import com.tasif.policyservice.repository.PolicyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService{

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Policy createPolicy(PolicyDto policyDto) {
        policyRepository.findPolicyByName(policyDto.getName()).ifPresent(p->{
            throw new PolicyAlreadyExistsException("Policy already exists with " + p.getName());
        });
        Policy policy = modelMapper.map(policyDto, Policy.class);
        System.out.println(policy);
        policy = policyRepository.save(policy);
        return policy;
    }

    @Override
    public Policy updatePolicy(Integer id, PolicyDto policyDto) {
        Policy policy = policyRepository.findById(id).orElseThrow(()->new PolicyNotFoundException("Policy not found"));
        modelMapper.map(policyDto, policy);
        System.out.println(policy);
        policy = policyRepository.save(policy);
        return policy;
    }

    @Override
    public Policy deletePolicy(Integer id) {
        Policy policy = policyRepository.findById(id).orElseThrow(()->new PolicyNotFoundException("Policy not found"));
        policyRepository.delete(policy);
        return policy;
    }

    @Override
    public List<Policy> getAllPolicy() {
        return policyRepository.findAll();
    }

    @Override
    public Policy getPolicy(Integer id) {
        Policy policy = policyRepository.findById(id).orElseThrow(()->new PolicyNotFoundException("Policy not found"));
        return policy;
    }
}
