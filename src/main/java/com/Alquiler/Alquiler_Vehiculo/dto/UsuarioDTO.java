package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import com.Alquiler.Alquiler_Vehiculo.model.UserRol;
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

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    private UserRol userRol;

    /*
    private String cedula;

    private String direccion;

    private Boolean isLicencia;

    private String genero;

    private Integer edad;
    */

    private Set<Reserva> reservas = new HashSet<Reserva>();

}
