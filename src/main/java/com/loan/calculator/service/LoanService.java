package com.loan.calculator.service;

import com.loan.calculator.model.Charges;
import com.loan.calculator.model.Request;
import com.loan.calculator.model.Response;
import com.loan.calculator.utils.Validation;
import org.springframework.stereotype.Service;

import static com.loan.calculator.constants.Constants.*;

@Service
public class LoanService {

    Charges charges = new Charges();
    double taxRate;
    public Response getLoanCalculations(Request request){
        Response response = new Response();
        Validation validation = new Validation();
        String message = validation.doValidate(request);
        if(!message.isBlank()){
            response.setStatus(401);
            response.setStatusMessage(message);
        }

        double amountToBorrow = request.getAmountToBorrow();
        response.setStatus(200);
        response.setStatusMessage("Loan Calculations Fetched Successfully");
        double processingFee = 0.03 * amountToBorrow;
        double exerciseDuty = 0.2 * processingFee;
        double takeHomeAmount = amountToBorrow - (LEGAL_FEES + processingFee + exerciseDuty);

        charges.setLegalFees(LEGAL_FEES);
        charges.setExerciseDuty(exerciseDuty);
        charges.setProcessingFees(processingFee);

        response.setTakeHomeAmount(takeHomeAmount);
        response.setCharges(charges);

        if(request.getInterestType().equals("Flat Rate")){
            taxRate = FLAT_RATE;
        }

        if(request.getInterestType().equals("Reducing Balance Rate")){
            taxRate = REDUCING_BALANCE_RATE;
        }


        int compoundingFrequency = getLoanFrequency(request.getPaymentFrequency());
        double interest = amountToBorrow * Math.pow((1 + (taxRate / compoundingFrequency)),
                (compoundingFrequency * request.getLoanPeriod())) - amountToBorrow;
        double amountToPay = amountToBorrow + interest;
        response.setAmountToPay(Math.round(amountToPay * 100.0) / 100.0);

        double installments = getInstallments(amountToPay, request.getLoanPeriod(), request.getPaymentFrequency());
        response.setInstallments(Math.round(installments * 100.0) / 100.0);

        return response;
    }

    private int getLoanFrequency(String loanFrequency){
        int frequency = 0;

        if(loanFrequency.equals("Monthly")){
            frequency = 1;
        }

        if(loanFrequency.equals("Quarterly")){
            frequency = 3;
        }

        if(loanFrequency.equals("6 Months")){
            frequency = 6;
        }

        if(loanFrequency.equals("Annually")){
            frequency = 12;
        }

        return frequency;
    }

    private double getInstallments(double amountToPay, int loanPeriod, String loanFrequency){
        double installments = 0;
        int frequency = getLoanFrequency(loanFrequency);
        double intermediary = amountToPay / loanPeriod;
        installments = intermediary / frequency;

        if(frequency == 1){
            installments = intermediary / 12;
        }

        if(frequency == 12){
            installments = intermediary;
        }

        return installments;
    }
}
