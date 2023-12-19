package com.tasif.enrollservice.service;

import com.tasif.enrollservice.dto.EnrollDto;
import com.tasif.enrollservice.exception.PaymentException;
import com.tasif.enrollservice.exception.UserAlreadyEnrolledException;
import com.tasif.enrollservice.exception.UserNotEnrolledException;
import com.tasif.enrollservice.feign.PolicyFeignClient;
import com.tasif.enrollservice.feign.UserFeignClient;
import com.tasif.enrollservice.model.Enroll;
import com.tasif.enrollservice.model.Policy;
import com.tasif.enrollservice.model.User;
import com.tasif.enrollservice.repository.EnrollRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;


@Service
@Slf4j
public class EnrollServiceImpl implements EnrollService{

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private PolicyFeignClient policyFeignClient;

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Enroll enrollUser(String username, Integer policyId, EnrollDto enrollDto) {

        User user = userFeignClient.getUserByName(username);
        Policy policy = policyFeignClient.getPolicy(policyId);
        enrollRepository.findByUserIdAndPolicyId(user.getId(),policyId).ifPresent(e->{
            throw new UserAlreadyEnrolledException("User already enrolled in policy");
        });

        log.info("User --> " +user);
        log.info("Policy --> " + policy);
        Enroll enroll = modelMapper.map(enrollDto,Enroll.class);
        enroll.setUserId(user.getId());
        enroll.setUserName(user.getName());
        enroll.setPolicyId(policy.getId());
        enroll.setPolicyName(policy.getName());
        enroll.setPolicyNo(UUID.randomUUID());
        enroll.setEnrollDate(LocalDate.now());
        log.info("Enroll --> " + enroll);
        enroll = enrollRepository.save(enroll);
        return enroll;
    }

    @Override
    public Enroll claim(String username, Integer policyId, Integer claimAmount) {
        User user = userFeignClient.getUserByName(username);
        Policy policy = policyFeignClient.getPolicy(policyId);
        Enroll enroll = enrollRepository.findByUserIdAndPolicyId(user.getId(), policy.getId()).orElseThrow(() ->
                new UserNotEnrolledException("User is not enrolled in policy"));

        if(claimAmount>enroll.getCoverageAmount()){
            throw new PaymentException("Claim amount is more than the coverage");
        }

        enroll.setCoverageAmount(enroll.getCoverageAmount() - claimAmount);
        enrollRepository.save(enroll);

        return enroll;
    }
}
