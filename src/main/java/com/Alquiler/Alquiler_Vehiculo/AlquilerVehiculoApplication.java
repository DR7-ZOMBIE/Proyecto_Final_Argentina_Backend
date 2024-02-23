package com.Alquiler.Alquiler_Vehiculo;

import org.apache.log4j.PropertyConfigurator;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlquilerVehiculoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlquilerVehiculoApplication.class, args);
	}

	// Definir un bean para ModelMapper
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
