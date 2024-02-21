package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;

import java.util.Set;

public interface IVehiculoServices <T> {
    VehiculoDTO save(T t);  // Guardar un vehiculo de tipo transfer object
    void deleteById(Long id);// Eliminar un vehiculo por su id
    T findbyId(Long id); // Buscar un vehiculo por id
    Set<T> findAllTSet();     // Buscar todos los vehiculos
    void deleteAll();  // Elminar todos los vehiculos

}
