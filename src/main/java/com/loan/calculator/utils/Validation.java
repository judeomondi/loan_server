package com.loan.calculator.utils;

import com.loan.calculator.model.Request;

public class Validation {
    public String doValidate(Request request){
        String message = "";

        if(request.getAmountToBorrow() <= 0){
            message = "The Amount you are Borrowing is less than 0";
        }

        if(request.getInterestType().isBlank()){
            message = "Interest Type is Empty or Null";
        }

        if(request.getPaymentFrequency().isBlank()){
            message = "Payment Frequency is Empty or Null";
        }

        if(request.getLoanPeriod() <= 0){
            message = "Loan Period is less than 0";
        }

        if(request.getStartDate().isBlank()){
            message = "Start Date is Empty or Null";
        }

        return message;
    }
}
