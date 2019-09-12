package com.sample.rest.web.dtos.mappers;

import com.sample.persistence.User;
import com.sample.rest.web.dtos.UserProfileMinimalDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMinimalMapper {
	
	UserProfileMinimalMapper INSTANCE = Mappers.getMapper(UserProfileMinimalMapper.class);
	
	UserProfileMinimalDto entityToDto(User user);
	
	User dtoToEntity(UserProfileMinimalDto userProfileMinimalDto);
	
	void updateUserFromDto(UserProfileMinimalDto userProfileMinimalDto, @MappingTarget User user);

}
