package com.Alquiler.Alquiler_Vehiculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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
    private LocalDate year;
    @Column(nullable = false)
    private String matricula;
    @Column(nullable = false)
    private Boolean isDisponible;
    @Column(nullable = false)
    private String observacion;

    // Muchas vehiculos tienen una categoria
    @JsonIgnore
    @JoinColumn( name = "categoria_id" , nullable = false)
    @ManyToOne ( fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    private Categoria categoria;

    // Un vehiculo tiene una reserva
    @JsonIgnore
    @OneToOne(mappedBy = "vehiculo", cascade = { CascadeType.MERGE })
    private Reserva reservas;

}
