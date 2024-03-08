package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Metodo_Pago;
import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioDTO {

    private Long ID;

    private String cedula;

    private String nombre;

    private String direccion;

    private Boolean isLicencia;

    private String genero;

    private Integer edad;

    private String email;

    private String password;

    private Metodo_Pago metodo_pago;

    private Set<Reserva> reservas = new HashSet<Reserva>();



}
