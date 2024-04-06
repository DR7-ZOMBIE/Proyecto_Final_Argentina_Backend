package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import com.Alquiler.Alquiler_Vehiculo.model.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UsuarioDTO {

    private Long ID;

    private String nombre;

    private String apellido;

    private String username;

    private String email;

    private String password;

    private Role role;

    private Set<Reserva> reservas = new HashSet<Reserva>();

}
