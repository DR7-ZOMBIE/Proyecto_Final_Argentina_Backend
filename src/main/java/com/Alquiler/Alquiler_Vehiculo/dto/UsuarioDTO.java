package com.Alquiler.Alquiler_Vehiculo.dto;

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

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    private String password2;

    private String role;

    private Set<Reserva> reservas = new HashSet<Reserva>();

}
