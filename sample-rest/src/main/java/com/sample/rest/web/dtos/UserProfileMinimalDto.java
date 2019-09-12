package com.sample.rest.web.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * Dto to return only the basic details, along with the _links to get the complete profile.
 * @author puspender
 *
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = false)
public class UserProfileMinimalDto extends ResourceSupport {

	@NotBlank @Pattern(regexp="^[a-zA-Z]+$", message="Name can only contain english characters") 
	private String firstName, lastName;
	
	private String bio, lifeVision, gender;
	private LocalDate dateOfBirth;
	private boolean ifPrincipal = false;
	
	public void setBio(String bio) {
		if (bio != null) {
			this.bio = bio.trim();
		}
		this.bio = bio;
	}
	
	public void setLifeVision(String lifeVision) {
		if (lifeVision != null) {
			this.lifeVision = lifeVision.trim();
		}
		this.lifeVision = lifeVision;
	}
	

}
