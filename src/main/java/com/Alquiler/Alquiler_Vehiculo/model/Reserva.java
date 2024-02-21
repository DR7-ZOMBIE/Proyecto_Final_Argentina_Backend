package com.Alquiler.Alquiler_Vehiculo.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
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
@Table ( name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column (nullable = false)
    private String ubicacion;

    @Column (nullable = false)
    private LocalDate fecha_Inicio;

    @Column (nullable = false)
    private LocalDate fecha_Entrega;

    @Column (nullable = false)
    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

    @Column (nullable = false)
    private Boolean isReserva;

    @Column (nullable = false)
    private Metodo_Pago metodo_Pago;


}
