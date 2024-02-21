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
    @Column ( unique = true , nullable = false)
    private Long ID;

    @Column
    private String ubicacion;

    @Column
    private LocalDate fecha_Inicio;

    @Column
    private LocalDate fecha_Entrega;

    @Column
    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

    @Column
    private Boolean isReserva;

    @Column
    private Metodo_Pago metodo_Pago;


}
