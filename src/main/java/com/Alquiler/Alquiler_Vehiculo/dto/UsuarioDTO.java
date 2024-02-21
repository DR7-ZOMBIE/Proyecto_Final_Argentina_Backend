package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Metodo_Pago;
import com.Alquiler.Alquiler_Vehiculo.model.Reserva;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {

    private Long ID;

    private String cedula;

    private String nombre;

    private String direccion;

    private Set<Reserva> reservas = new HashSet<Reserva>();

    private Metodo_Pago metodo_pago;

    private Boolean isLicencia;

    private String genero;

    private Integer edad;

    private String email;

}
