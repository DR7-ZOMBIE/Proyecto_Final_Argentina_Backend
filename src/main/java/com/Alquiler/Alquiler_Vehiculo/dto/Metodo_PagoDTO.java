package com.Alquiler.Alquiler_Vehiculo.dto;

import java.time.LocalDate;

public class Metodo_PagoDTO {

    private Long ID;

    private Boolean isCredDeb;

    private Double monto;

    private String tipo_Tarjeta;

    private LocalDate fecha_Vencimiento;

    private Integer CVE;

    private Integer numero_Tarjeta;

}
