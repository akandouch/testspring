package com.aka.testspring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class CustomFilterForApi extends BasicAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	public CustomFilterForApi(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("CustomFilterForApi : " + request.getParameterMap().toString());
				
		if( response instanceof HttpServletResponse) {
			
			System.out.println("response http");
			HttpServletResponse httpServletResp = (HttpServletResponse) response;
			HttpServletResponseWrapper respHTTP = new HttpServletResponseWrapper(httpServletResp) ;
			
			
			
			HttpServletRequest httpServletReq = (HttpServletRequest) request;
			HttpServletRequestWrapper reqHttp = new HttpServletRequestWrapper(httpServletReq);

			String authorisation = reqHttp.getHeader("Authorization");
			System.out.println("authorisation " + authorisation);
			
			if( authorisation == null || !authorisation.contains("Bearer") ) {
				respHTTP.sendError(401, "Not authorised please provide token");
			}
			chain.doFilter(request, respHTTP.getResponse());
		}else {
			System.out.println("response no http");
			chain.doFilter(request, response);
		}
		
	}

}
