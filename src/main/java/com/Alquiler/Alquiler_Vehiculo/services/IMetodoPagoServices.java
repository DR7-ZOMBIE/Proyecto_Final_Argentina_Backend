package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.MetodoPagoDTO;

import java.util.Set;

public interface IMetodoPagoServices<T> {

    MetodoPagoDTO save(T t); // Agregar metodo de pago

    void deleteById(Long id); // Elminar un metodo de pago

    T findById(Long id); // Buscar un metodo de pago

    Set<T> findAll(); // Listar todos los metodos de pago
    void deleteAll(); // Limpiar todos los metodos de pago

}
