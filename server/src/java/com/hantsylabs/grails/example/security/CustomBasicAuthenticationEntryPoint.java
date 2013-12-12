package com.hantsylabs.grails.example.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class CustomBasicAuthenticationEntryPoint extends
		BasicAuthenticationEntryPoint {

	private static Logger log = LoggerFactory
			.getLogger(CustomBasicAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// super.commence(request, response, authException);
		log.debug("call @ commence...");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

}
