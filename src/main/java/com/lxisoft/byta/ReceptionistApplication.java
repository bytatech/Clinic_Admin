package com.lxisoft.byta;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lxisoft.byta.repository.ReceptionistRepository;
import com.lxisoft.byta.security.SecurityUtils;



@EnableJpaAuditing
@SpringBootApplication
public class ReceptionistApplication {
	@Autowired
	private ReceptionistRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReceptionistApplication.class, args);

	}

	public @PostConstruct void init() {
		SecurityUtils.runAs("system", "system", "ROLE_RECEPTIONIST", "ROLE_PATIENT","ROLE_DOCTOR");
		// patientRepository.save(new PatientData("lxi", 1234L));

		SecurityContextHolder.clearContext();
	}

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.inMemoryAuthentication().//
					
					withUser("arun").password("hello").roles( "RECEPTIONIST").and().
					withUser("karthi").password("123").roles("DOCTOR");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.httpBasic().and().authorizeRequests().//
					antMatchers(HttpMethod.POST,"/receptionist/register").hasAnyRole("RECEPTIONIST").//
					antMatchers(HttpMethod.GET,"/patient/read").hasRole( "RECEPTIONIST").
					antMatchers(HttpMethod.PUT,"/patient/update").hasRole( "RECEPTIONIST").
					antMatchers(HttpMethod.GET,"/receptionist/generateToken").hasRole( "RECEPTIONIST").
					antMatchers(HttpMethod.GET,"/patient/searchbyname").hasRole( "RECEPTIONIST").
					antMatchers(HttpMethod.POST,"/privateData/save").hasAnyRole("PATIENT", "RECEPTIONIST").//
					antMatchers(HttpMethod.GET,"/privateData/read").hasRole( "RECEPTIONIST").
					antMatchers(HttpMethod.PUT,"/privateData/update").hasRole( "RECEPTIONIST").
					antMatchers(HttpMethod.DELETE,"/privateData/delete").hasRole( "RECEPTIONIST").
					antMatchers(HttpMethod.GET,"/doctor/read").hasRole( "DOCTOR").
					and().//
					csrf().disable();
		}
	}
}
