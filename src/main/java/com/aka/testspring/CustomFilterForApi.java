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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.security.web.FilterChainProxy;

public class CustomFilterForApi extends FilterChainProxy{
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//super.doFilter(request, response, chain);
		
		System.out.println(request.getParameterMap().toString());
		
		if( response instanceof HttpServletResponse) {
		HttpServletResponse httpServletResp = (HttpServletResponse) response;
		HttpServletResponseWrapper respHTTP = new HttpServletResponseWrapper(httpServletResp) ;
		respHTTP.setStatus(403);
		chain.doFilter(request, respHTTP.getResponse());}
		
	}

}
