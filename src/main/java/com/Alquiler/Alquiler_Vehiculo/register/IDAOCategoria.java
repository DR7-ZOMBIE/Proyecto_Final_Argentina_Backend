package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.dto.CategoriaDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDAOCategoria extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByTitulo(String titulo);
}

