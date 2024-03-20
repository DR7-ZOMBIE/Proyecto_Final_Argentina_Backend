package com.Alquiler.Alquiler_Vehiculo.dto;

import lombok.*;

import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoriaDTO {

    private Long id;

    private String nombre;

    private HashSet<VehiculoDTO> vehiculos = new HashSet<>();
}
