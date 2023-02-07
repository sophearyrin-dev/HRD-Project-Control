package com.kshrd.hrdprojectcontrolapi.configurations;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://35.220.202.124:5432/hrd_project_control_8th");
        driverManagerDataSource.setUsername("hrd_project_control");
        driverManagerDataSource.setPassword("hrdprojectcontrol!@#");
        return driverManagerDataSource;
    }
}