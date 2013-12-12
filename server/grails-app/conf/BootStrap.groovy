import org.junit.Before;

import grails.plugin.springsecurity.SecurityFilterPosition;
import grails.plugin.springsecurity.SpringSecurityUtils;

import com.hantsylabs.grails.example.domain.Book
import com.hantsylabs.grails.example.security.Authority
import com.hantsylabs.grails.example.security.Person
import com.hantsylabs.grails.example.security.PersonAuthority

class BootStrap {

    def init = { servletContext ->
		new Book(title:"Java Persistence with Hibernate", author:"Gavin King", price:99.00).save()
		new Book(title:"Spring Live", author:"Matt Raible", price:29.00).save()	
		
		def person =new Person(username:"test", password:"test123")
		person.save()
		
		def roleUser=new Authority(authority:"ROLE_USER")
		roleUser.save()
		
		new PersonAuthority(person:person, authority:roleUser).save()
		
//		for (String url in [
//			'/', '/index', '/index.gsp', '/**/favicon.ico',
//			'/**/js/**', '/**/css/**', '/**/images/**',
//			'/login', '/login.*', '/login/*',
//			'/logout', '/logout.*', '/logout/*']) {
//		 new Requestmap(url: url, configAttribute: 'permitAll').save()
//	  }
	//	SpringSecurityUtils.clientRegisterFilter("customBasicAuthenticationFilter", SecurityFilterPosition.BASIC_AUTH_FILTER.order+1)
	//	SpringSecurityUtils.clientRegisterFilter("statelessSecurityContextPersistenceFilter", SecurityFilterPosition.SECURITY_CONTEXT_FILTER.order+1)
    }
    def destroy = {
    }
}
