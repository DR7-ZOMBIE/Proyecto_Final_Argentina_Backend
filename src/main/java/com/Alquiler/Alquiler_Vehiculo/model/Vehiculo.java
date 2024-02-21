package com.Alquiler.Alquiler_Vehiculo.model;

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
    private Long ID;

    @Column (nullable = false, unique = true)
    private String matricula;

    @Column (nullable = false)
    private String marca;

    @Column (nullable = false)
    private String color;

    @Column (nullable = false)
    private Boolean isDisponible;

    @Column (nullable = false)
    private LocalDate fecha_Fabricacion;

    @Column (nullable = false)
    private String tipo_Combustible;

    // Ojo, revisar si será la categoría
    @Column (nullable = false)
    private String gama_Vehiculo;

    @Column (nullable = false)
    private Integer kilometraje;

    @Column (nullable = false)
    private Integer cantidad_Puertas;

    @Column (nullable = false)
    private String modelo_Auto;

    @Column (nullable = false)
    private String transmision;

    @Column (nullable = false)
    private Double precio;

    @Column
    private String descripcion;

}
