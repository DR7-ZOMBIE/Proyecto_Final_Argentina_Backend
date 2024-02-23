package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpresaDTO {

    private Long ID;

    private Integer razon_Social;

    private String telefono;

    private String email;

    private String direccion;

    Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

}
