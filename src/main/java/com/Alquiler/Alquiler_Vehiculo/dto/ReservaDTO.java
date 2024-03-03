package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Metodo_Pago;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

public class ReservaDTO {

    private Long ID;

    private String ubicacion;

    private LocalDate fecha_Inicio;

    private LocalDate fecha_Entrega;

    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

    private Boolean isReserva;

    private Metodo_Pago metodo_Pago;

}
