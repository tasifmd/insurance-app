package com.tasif.enrollservice.service;

import com.tasif.enrollservice.model.Payment;

public interface PaymentService {
    public Payment makePayment(String username, Integer policyId, Integer amount);
}
