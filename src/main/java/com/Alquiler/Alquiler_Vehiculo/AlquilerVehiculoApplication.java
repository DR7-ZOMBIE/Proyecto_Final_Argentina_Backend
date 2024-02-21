package com.Alquiler.Alquiler_Vehiculo;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlquilerVehiculoApplication {

	public static void main(String[] args) {
//		PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(AlquilerVehiculoApplication.class, args);
	}

}
