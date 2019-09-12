package com.sample.rest.web.dtos.mappers;

import com.sample.common.ParentEntity;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingInheritanceStrategy;
import org.springframework.hateoas.ResourceSupport;

/**
 * Configuration for all of the mapping interfaces. 
 * @author puspender
 *
 */
@MapperConfig(mappingInheritanceStrategy=MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface MappingCommonConfig {
	
	
	/**
	 * Used to ignore the id, link fields which gives error if the dtoToEntity operation is done.
	 * In that case mapper tries to map the id(for example) field of User(for example) entity with 
	 * the id field of {@link ResourceSupport}.
	 * @param resourceSupport
	 * @return
	 */
//	@Mapping(target = "id", ignore = true)
	ParentEntity map(ResourceSupport resourceSupport);

}
