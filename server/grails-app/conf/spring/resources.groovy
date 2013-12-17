import grails.plugin.springsecurity.SpringSecurityUtils

import org.springframework.security.web.access.AccessDeniedHandlerImpl
import org.springframework.security.web.access.ExceptionTranslationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.context.NullSecurityContextRepository
import org.springframework.security.web.context.SecurityContextPersistenceFilter
import org.springframework.security.web.savedrequest.NullRequestCache

import com.hantsylabs.grails.example.security.CustomBasicAuthenticationEntryPoint

// Place your Spring DSL code here
beans = {
	/** securityContextRepository */
	statelessSecurityContextRepository(NullSecurityContextRepository) {
	}

	/** securityContextPersistenceFilter */
	statelessSecurityContextPersistenceFilter(SecurityContextPersistenceFilter, ref('statelessSecurityContextRepository')) {
	}
	
	
	customBasicAuthenticationEntryPoint(CustomBasicAuthenticationEntryPoint) {
		realmName = SpringSecurityUtils.securityConfig.basic.realmName // 'Grails Realm'
	}
	
	customBasicAuthenticationFilter(BasicAuthenticationFilter, ref('authenticationManager'), ref('customBasicAuthenticationEntryPoint')) {
		authenticationDetailsSource = ref('authenticationDetailsSource')
		rememberMeServices = ref('rememberMeServices')
		credentialsCharset = SpringSecurityUtils.securityConfig.basic.credentialsCharset // 'UTF-8'
	}

	basicAccessDeniedHandler(AccessDeniedHandlerImpl)

	basicRequestCache(NullRequestCache)

	basicExceptionTranslationFilter(ExceptionTranslationFilter, ref('customBasicAuthenticationEntryPoint'), ref('basicRequestCache')) {
		accessDeniedHandler = ref('basicAccessDeniedHandler')
		authenticationTrustResolver = ref('authenticationTrustResolver')
		throwableAnalyzer = ref('throwableAnalyzer')
	}

}
