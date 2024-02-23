package com.Alquiler.Alquiler_Vehiculo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empresa")
public class Empresa {

    // Columna de ID - LLave primaria
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( unique=true , nullable=false )
    private Long ID;

    @Column
    private Integer razon_Social;

    @Column
    private String telefono;

    @Column
    private String direccion;

    // Lazy es carga lente primero verificar la relacion y de ahi si los carga
    // Eager carga de inmediato pero carga datos innecesarios
    // Como la relacion esta en empresa que una empresa tiene muchos vehiculos debemos proceder a instanciar
    // Una emprresa en vehiculos para que tenga sentido la relacion
    // Una empresa tiene muchos vehiculos

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

}
