package com.sample.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.sample.persistence"})
@EnableJpaRepositories(basePackages = {"com.sample.persistence"})
@PropertySource({"classpath:persistence-${persistenceTarget:mysql-test}.properties" })
public class PersistenceConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("jdbc.driverClassName")));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource());
		entityManager.setPackagesToScan("com.sample.persistence");
		final JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setJpaProperties(additionalProperties());
		return entityManager;

	}
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql", "false"));
		properties.setProperty("jpa.generateDdl", environment.getProperty("jpa.generateDdl", "true"));
		properties.setProperty("hibernate.jdbc.time_zone", environment.getProperty("hibernate.jdbc.time_zone"));
		//properties.setProperty("hibernate.id.new_generator_mappings","false");
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
