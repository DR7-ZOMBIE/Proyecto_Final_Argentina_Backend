package com.Alquiler.Alquiler_Vehiculo.dto;

import com.Alquiler.Alquiler_Vehiculo.model.Usuario;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Metodo_PagoDTO {

    private Long ID;

    private Boolean isCredDeb;

    private Double monto;

    private String tipo_Tarjeta;

    private LocalDate fecha_Vencimiento;

    private Integer cve;

    private Integer numero_Tarjeta;

    private Usuario usuario;

}
