package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import com.hcl.model.AuthRequest;
import com.hcl.service.JwtService; 
  
@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
    private AuthenticationManager authenticationManager; 
	@Autowired
    private JwtService jwtService; 
	 @PostMapping("/generateToken") 
	    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws AuthenticationException { 
		 
		 System.out.println("##################################  UserController");
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
	        if (authentication.isAuthenticated()) { 
	            return jwtService.generateToken(authRequest.getUsername()); 
	        } else { 
	            throw new UsernameNotFoundException("invalid user request !"); 
	        } 
	    } 
}
