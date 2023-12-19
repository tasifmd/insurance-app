package com.tasif.policyservice.repository;

import com.tasif.policyservice.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    Optional<Policy> findPolicyByName(String name);
}
