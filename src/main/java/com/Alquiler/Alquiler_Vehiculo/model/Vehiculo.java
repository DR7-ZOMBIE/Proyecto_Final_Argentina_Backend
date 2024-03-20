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
    private String matricula;

    @Column(nullable = false)
    private String modelo_Auto;
    @Column(nullable = false)
    private String kilometraje;
    @Column(nullable = false)
    private String serialChasis;
    @Column(nullable = false)
    private String serialMotor;
    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Boolean isDisponible;

    @Column(nullable = false)
    private LocalDate anio;

    @Column(nullable = false)
    private String tipo_Combustible;

    @Column(nullable = false)
    private String gama_Vehiculo;

    @Column(nullable = false)
    private Integer cantidad_Puertas;

    @Column(nullable = false)
    private String transmision;

    @Column(nullable = false)
    private Double precio;
    @Column(nullable = false)
    private String observacion;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean isFavorito;

    @ManyToMany(mappedBy = "vehiculos", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Reserva> reservas = new HashSet<>();

    // Muchas vehiculos tienen una categoria
    @JsonIgnore
    @JoinColumn( name = "categoria_id" , nullable = false)
    @ManyToOne ( fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Categoria categoria;

}
