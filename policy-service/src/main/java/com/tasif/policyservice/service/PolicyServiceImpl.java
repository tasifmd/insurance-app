package com.tasif.policyservice.service;

import com.tasif.policyservice.dto.PolicyDto;
import com.tasif.policyservice.exception.PolicyAlreadyExistsException;
import com.tasif.policyservice.exception.PolicyNotFoundException;
import com.tasif.policyservice.model.Policy;
import com.tasif.policyservice.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Policy createPolicy(PolicyDto policyDto) {
        log.info("Creating policy {}", policyDto.getName());
        policyRepository.findPolicyByName(policyDto.getName()).ifPresent(p -> {
            log.error("Policy already exists with {}", p.getName());
            throw new PolicyAlreadyExistsException("Policy already exists with " + p.getName());
        });
        Policy policy = modelMapper.map(policyDto, Policy.class);
        policy = policyRepository.save(policy);
        log.info("Policy is created successfully {}", policy);
        return policy;
    }

    @Override
    public Policy updatePolicy(Integer id, PolicyDto policyDto) {
        log.info("Updating policy {}", policyDto.getName());
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new PolicyNotFoundException("Policy not found"));
        modelMapper.map(policyDto, policy);
        System.out.println(policy);
        policy = policyRepository.save(policy);
        log.info("Policy is updated successfully {}", policy);
        return policy;
    }

    @Override
    public Policy deletePolicy(Integer id) {
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new PolicyNotFoundException("Policy not found"));
        log.info("Deleting policy {}", policy.getName());
        policyRepository.delete(policy);
        log.info("Policy is deleted successfully {}", policy);
        return policy;
    }

    @Override
    public List<Policy> getAllPolicy() {
        return policyRepository.findAll();
    }

    @Override
    public Policy getPolicy(Integer id) {
        log.info("Get policy by id");
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new PolicyNotFoundException("Policy not found"));
        return policy;
    }
}
