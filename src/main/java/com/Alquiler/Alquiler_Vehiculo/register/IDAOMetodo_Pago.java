package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.model.Metodo_Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDAOMetodo_Pago extends JpaRepository<Metodo_Pago, Long> { }
