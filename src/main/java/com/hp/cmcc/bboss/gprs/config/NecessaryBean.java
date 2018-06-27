package com.hp.cmcc.bboss.gprs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NecessaryBean {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(
	        @Qualifier("primaryDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
}
