package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Metodo_Pago;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ReservaDTO {

    private Long ID;

    private String ubicacion;

    private LocalDate fecha_Inicio;

    private LocalDate decha_Entrega;

    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

    private Boolean isReserva;

    private Metodo_Pago metodo_Pago;

}
