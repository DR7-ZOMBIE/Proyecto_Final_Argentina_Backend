package com.Alquiler.Alquiler_Vehiculo;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.Alquiler.Alquiler_Vehiculo.config")
public class AlquilerVehiculoApplication {

	public static void main(String[] args) {
//		PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(AlquilerVehiculoApplication.class, args);
	}

}
