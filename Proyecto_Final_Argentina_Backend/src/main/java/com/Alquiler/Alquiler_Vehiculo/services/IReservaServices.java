package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.ReservaDTO;

import java.util.Set;

public interface IReservaServices<T> {
    ReservaDTO save(T t); // Registrar una reserva
    void deleteById(Long id); // Elimina una reserva
    T findById(Long id); // Buscar una reserva
    Set<T> findAll(); // Guardar todas las reservas
    void deleteAll(); // Elminar todas las reservas

}
