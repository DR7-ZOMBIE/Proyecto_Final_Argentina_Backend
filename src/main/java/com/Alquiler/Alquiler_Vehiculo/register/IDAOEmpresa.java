package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDAOEmpresa extends JpaRepository<Empresa, Long> { }
