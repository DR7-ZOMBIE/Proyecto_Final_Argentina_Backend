package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CategoriaDTO {

    private Long id;

    private String titulo;

    private String descripcion;

    private String imagen;

    private Set<Vehiculo> vehiculos = new HashSet<>();

}
