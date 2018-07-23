package com.aka.testspring;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.server.handler.DefaultWebFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan("com.aka.*")
public class SecurityConfig extends WebSecurityConfigurerAdapter/* implements AuthenticationEntryPoint*/{
	
	//@Autowired
	//AuthenticationManagerBuilder amb;
	
	/*@Bean(name="springSecurityFilterChain")
	public DelegatingFilterProxy springSecurityFilterChain() {
		DelegatingFilterProxy filterChain = new DelegatingFilterProxy();
		return filterChain;
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("test").password("test").roles("testeur");
		//UsernamePasswordAuthenticationToken authUser = new UsernamePasswordAuthenticationToken("test", "test");
		//User.withDefaultPasswordEncoder().build();
		//authUser.setAuthenticated(true);
		//System.out.println("authenticated : " + authUser.isAuthenticated());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("iiiiiii");
		http.addFilterAfter(new CustomFilterForApi(), SwitchUserFilter.class);
	//	http.authorizeRequests().antMatchers(HttpMethod.GET).authenticated().and().formLogin();
		//authorizeRequests().antMatchers("*").hasRole("zaide").anyRequest().authenticated();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		System.out.println("oooo");
		//web.ignoring().antMatchers(HttpMethod.GET);
	}
	@PostConstruct
	public void test() {
		System.out.println("on va me contruier");
	}

	/*@Override
	public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		
		System.out.println("---->" + arg0.getLocale());
		
	}*/

}
