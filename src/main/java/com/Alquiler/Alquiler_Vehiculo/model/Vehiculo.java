package com.Alquiler.Alquiler_Vehiculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long ID;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private String serialMotor;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String year;
    @Column(nullable = false)
    private String matricula;
    @Column(nullable = false)
    private Boolean isDisponible;
    @Column(nullable = false)
    private Boolean isFavorito;
    @Column(nullable = false)
    private String observacion;

    // Muchas vehiculos tienen una categoria
    @JoinColumn( name = "categoria_id" , nullable = true)
    @ManyToOne ( fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JsonIgnore
    private Categoria categoria;

    // Un vehiculo tiene una reserva
    @OneToOne(mappedBy = "vehiculo", cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
    @JsonIgnore
    private Reserva reservas;

}
