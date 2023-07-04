package com.loan.calculator.controller;

import com.loan.calculator.model.Request;
import com.loan.calculator.model.Response;
import com.loan.calculator.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loan-calculator")
public class LoanController {

    @Autowired
    LoanService loanService;

    private static Logger logger = LoggerFactory.getLogger(LoanController.class);
    //@CrossOrigin
    @PostMapping("/getQuotation")
    public ResponseEntity<Response> getQuotation(@RequestBody Request request){
        logger.info("Request: "+request.toString());
        Response response = loanService.getLoanCalculations(request);
        logger.info("Response" + response.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:63342");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        headers.add("Access-Control-Allow-Methods", "POST");
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Max-Age", "10");
        headers.add("Access-Control-Expose-Headers", "*");
        return ResponseEntity.ok().headers(headers).body(response);
    }

}
