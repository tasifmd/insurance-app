package com.tasif.policyservice;

import com.tasif.policyservice.dto.PolicyDto;
import com.tasif.policyservice.model.Policy;
import com.tasif.policyservice.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody PolicyDto policyDto) {
        Policy response = policyService.createPolicy(policyDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Integer policyId, @RequestBody PolicyDto policyDto) {
        Policy response = policyService.updatePolicy(policyId, policyDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<Policy> deletePolicy(@PathVariable Integer policyId) {
        Policy response = policyService.deletePolicy(policyId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<Policy> getPolicy(@PathVariable Integer policyId) {
        Policy response = policyService.getPolicy(policyId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicy() {
        List<Policy> response = policyService.getAllPolicy();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
