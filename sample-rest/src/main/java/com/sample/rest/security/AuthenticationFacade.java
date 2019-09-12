package com.sample.rest.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Facade which returns the current authentication details.
 * @author puspender
 *
 */
@Component
public class AuthenticationFacade {

	public Authentication getAuthenticationPrincipal() {
		return  SecurityContextHolder.getContext().getAuthentication();
	}
}
