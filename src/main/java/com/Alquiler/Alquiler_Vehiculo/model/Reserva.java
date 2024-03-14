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

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private LocalDate fecha_Inicio;

    @Column(nullable = false)
    private LocalDate fecha_Entrega;

    @Column(nullable = false)
    private Boolean isReserva;

    @Column(nullable = false)
    private Boolean isDebit; // true debito - false credito

    // Muchas reservas tienen un usuario
    @JsonIgnore
    @JoinColumn( name = "usuario_id" , nullable = false)
    @ManyToOne ( fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Usuario usuario;

    // Muchos vehiculos tienen una reserva
    @ManyToMany
    @JoinTable(
            name = "reserva_vehiculo",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "vehiculo_id")
    )
    private Set<Vehiculo> vehiculos = new HashSet<>();

}
