package com.loan.calculator.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
}
