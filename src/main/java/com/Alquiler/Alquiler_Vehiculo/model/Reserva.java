package com.Alquiler.Alquiler_Vehiculo.model;

import com.Alquiler.Alquiler_Vehiculo.model.user.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table ( name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column ( unique = true , nullable = false)
    private Long ID;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private LocalDate fecha_Inicio;

    @Column(nullable = false)
    private LocalDate fecha_Entrega;

    @Column(nullable = false)
    private Boolean isReserva;


    // Muchas reservas tienen un usuario
    @JoinColumn( name = "usuario_id" , nullable = true)
    @ManyToOne ( fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JsonBackReference // Evita la serialización de este lado para prevenir bucles
    private Usuario usuario;

    // Una reserva tiene un metodo de pago
    @OneToOne(cascade= CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name= "pago_id", nullable = true)
    private MetodoPago metodoDePago;

    // Una reserva tiene un vehiculo
    @OneToOne(cascade= CascadeType.MERGE , fetch = FetchType.EAGER)
    @JoinColumn(name= "vehiculo_id", nullable = true)
    private Vehiculo vehiculo;

}
