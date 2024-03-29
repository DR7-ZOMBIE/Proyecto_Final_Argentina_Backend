package com.Alquiler.Alquiler_Vehiculo.model;

import com.Alquiler.Alquiler_Vehiculo.model.user.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn( name = "usuario_id" , nullable = false)
    @ManyToOne ( fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Usuario usuario;

    // Una reserva tiene un metodo de pago
    @OneToOne(cascade= CascadeType.ALL )
    @JoinColumn(name= "pago_id", referencedColumnName = "id")
    private MetodoPago metodoDePago;

    // Una reserva tiene un vehiculo
    @OneToOne(cascade= CascadeType.ALL )
    @JoinColumn(name= "vehiculo_id", referencedColumnName = "id")
    private Vehiculo vehiculo;

}
