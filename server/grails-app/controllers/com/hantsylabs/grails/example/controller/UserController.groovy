package com.hantsylabs.grails.example.controller

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils

import javax.servlet.http.HttpServletResponse

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;

class UserController {
	
	def springSecurityService
	def userCache

	def authenticate() {
		if(springSecurityService.isLoggedIn()) {
			render([user:springSecurityService.currentUser] as JSON)
		}else {
			response.sendError HttpServletResponse.SC_UNAUTHORIZED
		}
	}
	
	def unauthenticate() {
		SecurityContextHolder.clearContext()
		response.setStatus(200)
	}
}
