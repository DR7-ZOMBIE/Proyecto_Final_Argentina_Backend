package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.EmpresaDTO;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface IEmpresaServices<T> {

   EmpresaDTO save(T t); // Metodo para guardar la empresa

   void delete (Long id); // Metodo para borrar la empresa

   T findById(Long id); // Metodo para buscarr una empresa

   Set<T> findAll(); // Metodo para buscar a todas las empresas

   void deleteAll(); // Metodo para eliminar todos los registros de la empresa


}
