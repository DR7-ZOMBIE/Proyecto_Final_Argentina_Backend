package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
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
    private String modelo_Auto;
    private Integer categoriaId;
    private String serialChasis;
    private String serialMotor;
    private String color;
    private LocalDate anio;
    private String matricula;
    private Boolean isDisponible;
    private String observacion;
    private String descripcion;
    private Set<Reserva> reservas = new HashSet<>();

    public void setId(long l) {
    }
}
