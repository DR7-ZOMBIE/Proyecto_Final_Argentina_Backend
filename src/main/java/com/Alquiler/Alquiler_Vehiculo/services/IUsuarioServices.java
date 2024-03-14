package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;

import java.util.Set;

public interface IUsuarioServices <T> {
    UsuarioDTO save(T t); // Guardar a un usuario en el sistema
    void deleteById(Long id); // Elminar un usuario por id
    T findById(Long id);  // Buscar un usuario especifico por su id
    Set<T> findAll(); // Listar todos los usuarios
    void deleteAll(); // Elminar todos los usuarios

}
