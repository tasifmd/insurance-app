package com.tasif.enrollservice.service;

import com.tasif.enrollservice.exception.PaymentException;
import com.tasif.enrollservice.exception.UserNotEnrolledException;
import com.tasif.enrollservice.feign.PolicyFeignClient;
import com.tasif.enrollservice.feign.UserFeignClient;
import com.tasif.enrollservice.model.Enroll;
import com.tasif.enrollservice.model.Payment;
import com.tasif.enrollservice.model.Policy;
import com.tasif.enrollservice.model.User;
import com.tasif.enrollservice.repository.EnrollRepository;
import com.tasif.enrollservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private PolicyFeignClient policyFeignClient;

    @Autowired
    private EnrollRepository enrollRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment makePayment(String username, Integer policyId, Integer amount) {
        User user = userFeignClient.getUserByName(username);
        Policy policy = policyFeignClient.getPolicy(policyId);
        Enroll enroll = enrollRepository.findByUserIdAndPolicyId(user.getId(), policy.getId()).orElseThrow(() ->
                new UserNotEnrolledException("User is not enrolled in policy"));

        Payment payment = new Payment();
        payment.setUserId(user.getId());
        payment.setPolicyId(policy.getId());
        payment.setTransactionId(UUID.randomUUID());
        payment.setAmount(amount);
        Integer paidAmount = Objects.requireNonNullElse(enroll.getAmountPaid(), 0);
        Integer outStandingAmount = enroll.getPolicyAmount() - paidAmount;
        if (amount > outStandingAmount) {
            throw new PaymentException("Amount is grater than the outstanding amount");
        }

        outStandingAmount = outStandingAmount - amount;
        enroll.setAmountPaid(paidAmount + amount);
        if (outStandingAmount == 0) {
            enroll.setFullypaid(true);
        }

        paymentRepository.save(payment);
        enrollRepository.save(enroll);
        return payment;
    }
}
