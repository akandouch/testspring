package com.aka.testspring.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("authencication authenticate()");
		return authentication;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	public JwtAuthenticationProvider() {
		// TODO Auto-generated constructor stub
	}

}
