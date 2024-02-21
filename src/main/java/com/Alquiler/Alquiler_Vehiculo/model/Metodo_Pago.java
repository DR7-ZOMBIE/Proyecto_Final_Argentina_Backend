package com.Alquiler.Alquiler_Vehiculo.model;

import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table ( name = "metodo_Pago")
public class Metodo_Pago {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private Boolean isCredDeb;

    @Column (nullable = false)
    private Double monto;

    @Column (nullable = false)
    private String tipo_Tarjeta;

    @Column (nullable = false)
    private LocalDate fecha_Vencimiento;

    @Column (nullable = false)
    private Integer CVE;

    @Column (nullable = false)
    private Integer numero_Tarjeta;

}
