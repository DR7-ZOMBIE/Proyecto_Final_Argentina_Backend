package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IDAOCategoria extends JpaRepository<Categoria, Long> { }
