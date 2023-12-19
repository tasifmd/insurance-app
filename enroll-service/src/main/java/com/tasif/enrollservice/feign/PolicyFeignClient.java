package com.tasif.enrollservice.feign;

import com.tasif.enrollservice.model.Policy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POLICY-SERVICE", url = "http://localhost:9091/policy")
public interface PolicyFeignClient {
    @GetMapping("/{policyId}")
    Policy getPolicy(@PathVariable Integer policyId);
}
