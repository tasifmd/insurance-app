package com.tasif.enrollservice.controller;

import com.tasif.enrollservice.model.Payment;
import com.tasif.enrollservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makepayment")
    public ResponseEntity<Payment> makePayment(@RequestParam String username, @RequestParam Integer policyId, @RequestParam Integer amount) {
        Payment response = paymentService.makePayment(username,policyId,amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
