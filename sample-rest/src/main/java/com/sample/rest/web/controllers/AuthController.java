package com.sample.rest.web.controllers;

import com.sample.rest.service.UserService;
import com.sample.rest.web.dtos.UserMinimalDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;		
	}

	/**
	 * Idea: This path is protected using basic auth. Client hit this path to login, Spring handles the basic auth and this path would return
	 * some basic details of User in return.
	 * @param request HttpServletRequest
	 * @return User's minimal details as {@link UserMinimalDto}
	 */
	@GetMapping("/login")
	@ResponseStatus(value = HttpStatus.OK)
	public HttpEntity<UserMinimalDto> loginUser(final HttpServletRequest request) {
		System.out.println("me too changing");
		final String authEmail = request.getUserPrincipal().getName();

		UserMinimalDto userMinimal = userService.getMinimalDetails(authEmail);

		return new ResponseEntity<>(userMinimal, HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	@ResponseStatus(value=HttpStatus.OK)
	public void logoutUser(@CookieValue(name="acctk") Cookie cookie ,final HttpServletRequest request, final HttpServletResponse response) {

		cookie.setValue("");
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath());
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
	}


}
