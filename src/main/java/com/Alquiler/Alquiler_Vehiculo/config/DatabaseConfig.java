package com.Alquiler.Alquiler_Vehiculo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories("com.Alquiler.Alquiler_Vehiculo.repository")
public class DatabaseConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public synchronized DataSource dataSource() {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
