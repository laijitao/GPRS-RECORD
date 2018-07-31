package com.hp.cmcc.bboss.bdc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BdcDataSourceConfig {
	
//	@Bean(name = "mysqlJdbcTemplate")
//	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
//	    return new JdbcTemplate(dataSource);
//	}
//	
//	@Bean(name = "mysqlDataSource")
//    @Qualifier("mysqlDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.mysql")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
	
	@Bean(name = "JdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("DataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "DataSource")
    @Qualifier("DataSource")
    @ConfigurationProperties(prefix="spring.datasource.oracle")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
