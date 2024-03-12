package com.Alquiler.Alquiler_Vehiculo.services;


import com.Alquiler.Alquiler_Vehiculo.dto.Metodo_PagoDTO;

import java.util.Set;

public interface IMetodo_PagoServices<T> {

    Metodo_PagoDTO save(T t); // Guardar un metodo de pago DTO

    void deleById(Long id);  // Eliminar un metodo de pago

    T findById(Long id); // Buscar un metodo de pago

    Set<T> findAll();  // Validar todos los pagos

    void deleteAll(); // Eliminar los pagos

}
