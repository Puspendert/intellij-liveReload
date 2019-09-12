package com.sample.rest.service;

import com.sample.rest.web.dtos.UserMinimalDto;

public interface UserService {
	UserMinimalDto getMinimalDetails(String email);

}
