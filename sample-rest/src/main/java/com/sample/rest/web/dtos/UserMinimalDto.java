package com.sample.rest.web.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 * For just the basic details of User(for client, it will return the data for
 * website header)
 * 
 * @author puspender
 *
 */

@Setter @Getter
@EqualsAndHashCode(callSuper = false)
public class UserMinimalDto extends ResourceSupport {

	private String firstName, lastName, uniqueId, profileAvatarURL;

}
