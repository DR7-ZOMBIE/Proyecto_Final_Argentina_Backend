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



    //EMPRESA YA NO ES NECESARIO... ELIMINAR?


    // Columna de ID - LLave primaria
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column
    private Integer razon_Social;

    @Column
    private String telefono;

    @Column
    private String direccion;

    @Column
    private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

}
