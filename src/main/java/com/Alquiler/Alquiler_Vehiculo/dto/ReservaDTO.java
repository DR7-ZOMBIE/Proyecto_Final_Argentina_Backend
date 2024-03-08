package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Metodo_Pago;
import com.Alquiler.Alquiler_Vehiculo.model.Usuario;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservaDTO {

    private Long ID;

    private String ubicacion;

    private LocalDate fecha_Inicio;

    private LocalDate fecha_Entrega;

    private Boolean isReserva;

    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

    private Usuario usuario;

}
