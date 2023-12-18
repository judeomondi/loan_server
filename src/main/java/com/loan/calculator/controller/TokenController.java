package com.loan.calculator.controller;

import com.loan.calculator.model.TokenRequest;
import com.loan.calculator.model.TokenResponse;
import com.loan.calculator.service.TokenUserDetails;
import com.loan.calculator.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("api")
public class TokenController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private TokenUserDetails userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest) throws Exception {

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = tokenUtil.generateToken(userDetails);

        Date expiration = tokenUtil.getExpirationDateFromToken(token);
        int expiry = (int) ((int)expiration.getTime() - new Date().getTime()) / 1000;

        return ResponseEntity.ok(new TokenResponse(expiry,token));
    }


}
