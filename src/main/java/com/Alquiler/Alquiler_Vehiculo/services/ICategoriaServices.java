package com.Alquiler.Alquiler_Vehiculo.services;

import java.util.Set;

public interface ICategoriaServices <T>{

    T save(T t); // Agregar categoria

    void deleteById(Long id); // Elminar una categoria

    T findById(Long id); // Buscar una categoria

    Set<T> findAll(); // Listar todas las categorias
    void deleteAll(); // Limpiar todas las categorias
    T findByTitulo(String titulo); // Buscar una categoria por titulo

}
