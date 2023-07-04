package com.loan.calculator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
    private double amountToBorrow;
    private String paymentFrequency;
    private int loanPeriod;
    private String startDate;
    private String interestType;
}
