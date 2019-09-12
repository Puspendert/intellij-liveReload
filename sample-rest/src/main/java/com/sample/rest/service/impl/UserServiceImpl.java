package com.sample.rest.service.impl;

import com.sample.persistence.User;
import com.sample.persistence.UserDao;
import com.sample.rest.service.UserService;
import com.sample.rest.web.dtos.UserMinimalDto;
import com.sample.rest.web.dtos.mappers.UserMinimalMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserMinimalDto getMinimalDetails(final String email) {
		User user = userDao.getUserMinimalByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		return UserMinimalMapper.INSTANCE.entityToDto(user);
	}


}
