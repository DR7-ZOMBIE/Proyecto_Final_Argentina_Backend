package com.Alquiler.Alquiler_Vehiculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table ( name = "vehiculo")
public class Vehiculo {

    // Columna llave primaria

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column ( unique = true , nullable = false)
    private Long ID;

    @Column
    private String matricula;

    @Column
    private String marca;

    @Column
    private String color;

    @Column
    private Boolean isDisponible;

    @Column
    private LocalDate fecha_Fabricacion;

    @Column
    private String tipo_Combustible;

    @Column
    private String gama_Vehiculo;

    @Column
    private Integer kilometraje;

    @Column
    private Integer cantidad_Puertas;

    @Column
    private String modelo_Auto;

    @Column
    private String transmision;

    @Column
    private Double precio;

    @Column
    private String descripcion;

    // JoinColumn es una relacion unidireccional mappedBy es Bidireccional
    // Muchos vehiculos tienen una empresa
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn( name = "empresa_id", nullable = false )
    private Empresa empresa;

    // JoinColumn es una relacion unidireccional mappedBy es Bidireccional
    // Muchos vehiculos tienen una reserva
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn( name = "reserva_id", nullable = false )
    private Reserva reserva;

}
