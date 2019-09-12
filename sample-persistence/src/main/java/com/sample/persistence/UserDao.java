package com.sample.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	Optional<User> getUserMinimalByEmail(String email);
	

}
