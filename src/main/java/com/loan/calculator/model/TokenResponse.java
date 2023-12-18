package com.loan.calculator.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {
    //private static final long serialVersionUID = -8091879091924046844L;
    private final String token_type="Bearer";
    private final int expires_in;
    private final String access_token;
}
