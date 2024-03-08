package com.Alquiler.Alquiler_Vehiculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // Una reserva tiene muchos vehiculos
    @OneToMany(mappedBy = "reserva" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

    @Column
    private Boolean isReserva;

    // Una reserva tiene un metodo de pago
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Metodo_Pago metodo_Pago;

    // Muchas reservas tienen un usuario
    @JsonIgnore
    @JoinColumn( name = "usuario_id" , nullable = false)
    @ManyToOne ( fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Usuario usuario;

}
