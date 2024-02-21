package com.Alquiler.Alquiler_Vehiculo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter

public class VehiculoDTO {

    private Long ID;

    private String matricula;

    private String marca;

    private String color;

    private Boolean isDisponible;

    private LocalDate fecha_Fabricacion;

    private String tipo_Combestible;

    private String gama_Vehiculo;

    private Integer kilometraje;

    private Integer cantidad_Puertas;

    private String modelo_Auto;

    private String transmision;

    private Double precio;

    private String descripcion;


}
