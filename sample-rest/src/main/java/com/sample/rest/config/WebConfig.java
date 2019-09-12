package com.sample.rest.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@ComponentScan(basePackages = { "com.sample.rest", "com.sample.common" })
public class WebConfig implements WebMvcConfigurer {

	public WebConfig() {
		super();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// captcha config
	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(3 * 1000);
		factory.setReadTimeout(7 * 1000);

		return factory;
	}

	@Bean
	public RestOperations restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// Here we add our custom-configured HttpMessageConverter
		converters.add(jacksonMessageConverter());
		// super.configureMessageConverters(converters);
	}

	private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

		ObjectMapper mapper = new ObjectMapper();
		// Registering Hibernate4Module to support lazy objects
		mapper.registerModule(new Hibernate5Module());

		//enable JsonView feature
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

		messageConverter.setObjectMapper(mapper);
		return messageConverter;

	}

}
