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
    private Long ID;

    @Column (nullable = false, unique = true)
    private String cedula;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reserva> reservas = new HashSet<Reserva>();

    @Column (nullable = false)
    private Metodo_Pago metodo_Pago;

    @Column (nullable = false)
    private Boolean isLicencia;

    @Column (nullable = false)
    private String genero;

    @Column(nullable = false)
    private String password;

    @Column (nullable = false)
    private Integer edad;

    @Column (nullable = false)
    private String email;


}
