package com.Alquiler.Alquiler_Vehiculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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

    @Column(nullable = false)
    private Boolean isCredDeb;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String tipo_Tarjeta;

    @Column(nullable = false)
    private LocalDate fecha_Vencimiento;

    @Column(nullable = false)
    private Integer cve;

    @Column(nullable = false)
    private Integer numero_Tarjeta;

    // Un metodo de pago tiene una reserva
    @OneToOne(mappedBy = "metodoPago", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario Usuario;

}
