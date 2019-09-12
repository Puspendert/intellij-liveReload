package com.sample.rest.web.dtos.mappers;

import com.sample.persistence.User;
import com.sample.rest.web.dtos.UserMinimalDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MappingCommonConfig.class)
public interface UserMinimalMapper {
	
	UserMinimalMapper INSTANCE = Mappers.getMapper(UserMinimalMapper.class) ;
	
	UserMinimalDto entityToDto(User user);
	
}
