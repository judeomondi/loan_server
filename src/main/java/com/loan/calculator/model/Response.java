package com.loan.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data

public class Response {

    private int status;
    private String statusMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double installments;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double takeHomeAmount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Charges charges;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double amountToPay;
}
