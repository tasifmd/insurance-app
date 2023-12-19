package com.tasif.enrollservice.repository;

import com.tasif.enrollservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
