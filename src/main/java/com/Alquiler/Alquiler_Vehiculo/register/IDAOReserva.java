package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDAOReserva extends JpaRepository<Reserva, Long> {

    @EntityGraph(attributePaths = {"usuario", "metodoDePago", "vehiculo"})
    @NonNull
    List<Reserva> findAll();
}
