package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDAOUsuario extends JpaRepository<Usuario,Long> { }
