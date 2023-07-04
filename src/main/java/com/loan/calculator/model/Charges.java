package com.loan.calculator.model;

import lombok.Data;

@Data
public class Charges {
    public double legalFees;
    private double processingFees;
    private double exerciseDuty;
}
