package com.tasif.enrollservice.controller;

import com.tasif.enrollservice.dto.EnrollDto;
import com.tasif.enrollservice.model.Enroll;
import com.tasif.enrollservice.model.Policy;
import com.tasif.enrollservice.model.User;
import com.tasif.enrollservice.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @PostMapping("/enroll-user")
    public ResponseEntity<Enroll> enrollUserToPolicy(@RequestParam String username, @RequestParam Integer policyId, @RequestBody EnrollDto enrollDto) {
        Enroll response = enrollService.enrollUser(username, policyId,enrollDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/claim")
    public ResponseEntity<Enroll> claimPolicy(@RequestParam String username,@RequestParam  Integer policyId,@RequestParam  Integer claimAmount){
        Enroll response = enrollService.claim(username,policyId,claimAmount);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
