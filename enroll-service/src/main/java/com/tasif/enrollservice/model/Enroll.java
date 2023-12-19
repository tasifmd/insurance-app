package com.tasif.enrollservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private UUID policyNo;

    private Integer userId;

    private String userName;

    private Integer policyId;

    private String policyName;

    private Integer amountPaid;

    private Integer policyAmount;

    private Integer coverageAmount;

    private boolean isFullypaid;

    private LocalDate enrollDate;

    private LocalDate expireDate;

}
