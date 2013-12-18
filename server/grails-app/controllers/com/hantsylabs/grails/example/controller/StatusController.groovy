package com.hantsylabs.grails.example.controller

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils

import javax.servlet.http.HttpServletResponse

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;

class StatusController {
	
	def springSecurityService
	def userCache

	def index() {
		if(springSecurityService.isLoggedIn()) {
			render([user:springSecurityService.currentUser] as JSON)
		}else {
			response.sendError HttpServletResponse.SC_UNAUTHORIZED
		}
	}
	
}
