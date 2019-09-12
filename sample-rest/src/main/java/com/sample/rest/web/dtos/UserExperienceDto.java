package com.sample.rest.web.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserExperienceDto extends ResourceSupport {

	String designation, companyName, jobRole, country, state, city;
	LocalDate fromDate, toDate;
	
}
