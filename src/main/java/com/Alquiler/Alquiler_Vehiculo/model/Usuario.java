package com.Alquiler.Alquiler_Vehiculo.model;

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

    @Column
    private String cedula;

    @Column
    private String nombre;

    @Column
    private String direccion;

    @OneToMany( mappedBy = "usuario" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reserva> reservas = new HashSet<Reserva>();

    // Un usuario tiene un metodo de pago recordemos que el mapped by debe contener el atributo de la otra clase
    @OneToOne(mappedBy = "usuario" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Metodo_Pago metodo_Pago;

    @Column
    private Boolean isLicencia;

    @Column
    private String genero;

    @Column
    private String password;

    @Column
    private Integer edad;

    @Column
    private String email;


}
