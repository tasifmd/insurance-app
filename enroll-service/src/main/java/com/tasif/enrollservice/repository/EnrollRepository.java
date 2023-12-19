package com.tasif.enrollservice.repository;

import com.tasif.enrollservice.model.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollRepository extends JpaRepository<Enroll, Integer> {
    Optional<Enroll> findByUserIdAndPolicyId(Integer userid, Integer policyId);

}
