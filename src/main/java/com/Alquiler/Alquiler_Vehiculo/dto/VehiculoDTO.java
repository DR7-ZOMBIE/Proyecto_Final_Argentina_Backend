package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Categoria;
import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehiculoDTO {

    private Long ID;
    private String marca;
    private String modelo;
    private String serialMotor;
    private String color;
    private String year;
    private String matricula;
    private Boolean isDisponible;
    private String observacion;
    private Boolean isFavorito;

    private Categoria categoria;

    private Reserva reservas;

}
