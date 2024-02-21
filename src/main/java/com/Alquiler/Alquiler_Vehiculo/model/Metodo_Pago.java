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
    @Column ( unique = true , nullable = false)
    private Long ID;

    @Column
    private Boolean isCredDeb;

    @Column
    private Double monto;

    @Column
    private String tipo_Tarjeta;

    @Column
    private LocalDate fecha_Vencimiento;

    @Column
    private Integer CVE;

    @Column
    private Integer numero_Tarjeta;
}
