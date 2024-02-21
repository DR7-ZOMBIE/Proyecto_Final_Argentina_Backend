package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDAOVehiculo extends JpaRepository<Vehiculo, Long> { }
