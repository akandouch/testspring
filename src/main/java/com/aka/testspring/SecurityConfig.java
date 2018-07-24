package com.aka.testspring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aka.testspring.auth.JwtAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan("com.aka.*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtAuthenticationProvider jwtAuthenticationProvider;
	
	
	//@Override
	protected void noconfigure(AuthenticationManagerBuilder auth) throws Exception {
		//this.authenticationManager = auth.getObject();
		//auth.authenticationProvider(jwtAuthenticationProvider);
		//auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("test").password("test").roles("testeur");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("iiiiiii");
		
		
		http.csrf().disable();
		
		System.out.println("is authmanager null ? " + authenticationManager());
		http.antMatcher("/product/*").addFilter(new CustomFilterForApi(this.authenticationManager()));
		//http.antMatcher("/**").authenticationProvider(jwtAuthenticationProvider);
		/*http.authorizeRequests().anyRequest().authenticated()
        .and()
        .httpBasic();*/
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		System.out.println("oooo");
	}
}
