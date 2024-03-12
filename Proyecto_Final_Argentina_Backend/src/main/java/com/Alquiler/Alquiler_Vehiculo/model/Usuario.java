package com.Alquiler.Alquiler_Vehiculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table ( name = "user")
public class Usuario {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column ( unique = true , nullable = false)
    private Long ID;

    @Column(nullable = false)
    private String cedula;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Boolean isLicencia;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private String email;

    @OneToMany( mappedBy = "usuario" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reserva> reservas = new HashSet<Reserva>();

    // Un metodos de pago tienen una reserva
    @OneToOne
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private Metodo_Pago metodoPago;

}
