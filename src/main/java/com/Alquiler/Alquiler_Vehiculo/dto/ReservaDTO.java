package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.MetodoPago;
import com.Alquiler.Alquiler_Vehiculo.model.user.Usuario;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
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
@Data
public class ReservaDTO {

    private Long ID;

    private String ubicacion;

    private LocalDate fecha_Inicio;

    private LocalDate fecha_Entrega;

    private Boolean isReserva;

    private Usuario usuario;

    private MetodoPago metodoDePago;

    private Vehiculo vehiculo;

}
