package com.Alquiler.Alquiler_Vehiculo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class Metodo_PagoDTO {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ID;

    private Boolean isCredDeb;

    private Double monto;

    private String tipo_Tarjeta;

    private LocalDate fecha_Vencimiento;

    private Integer CVE;

    private Integer numero_Tarjeta;

}
