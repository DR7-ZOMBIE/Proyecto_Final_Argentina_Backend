package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MetodoPagoDTO {

    private Long id;

    private String nombreTitular;

    private String numeroDeTarjeta;

    private Integer cve;

    private Integer anioVencimiento;

    private Reserva reserva;


}
