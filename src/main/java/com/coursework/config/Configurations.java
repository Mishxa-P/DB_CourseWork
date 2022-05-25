package com.coursework.config;


import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Configuration;



import javax.sql.DataSource;


@Configuration
public class Configurations
{
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;database=Hotel");
        dataSource.setUsername("Accel");
        dataSource.setPassword("accel123");

        return dataSource;
    }


}