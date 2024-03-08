package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.ReservaDTO;
import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import com.Alquiler.Alquiler_Vehiculo.services.IReservaServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/reserva")
public class ControllerMYSQLReserva {

    @Autowired
    private IReservaServices<ReservaDTO> reservaServices; // Instanciar todos los servicios de reservas

    @Autowired
    private ModelMapper mapper; // Tranformar un objeto DTO

    // Listar todas las reservas
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public Set<ReservaDTO> listReservas(){

        Optional<Set<ReservaDTO>> reservas = Optional.ofNullable(reservaServices.findAll());

        if (reservas.isPresent()){
            return reservaServices.findAll();
        }else{
            throw new Excepciones("No hay reservas disponibles", HttpStatus.NOT_FOUND);
        }

    }

    // Buscar un reserva
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/id")
    public ReservaDTO findReserva(@RequestParam Long id){

        Optional<ReservaDTO> reserva = Optional.ofNullable(reservaServices.findById(id));

        if(reserva.isPresent()){
            return reservaServices.findById(id);
        }else{
            throw new Excepciones("La reserrva no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una reserva
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteReserva(@RequestParam Long id){

        Optional<ReservaDTO> reservaDTO = Optional.ofNullable(reservaServices.findById(id));

        if (!reservaDTO.isPresent()) throw new Excepciones("La reserva no existe", HttpStatus.NOT_FOUND);

        reservaServices.deleteById(id);
    }

    // Agregar una reserva
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<ReservaDTO> addReserva(@RequestBody ReservaDTO reservaDTO){
        reservaServices.save(reservaDTO);
        return ResponseEntity.ok(reservaServices.save(reservaDTO));
    }

    // Modificar una reserva
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public void updateReserva(@RequestBody ReservaDTO reservaDTO , @RequestParam Long id){

        Optional<ReservaDTO> r = Optional.ofNullable(reservaServices.findById(id));
        ReservaDTO r2 = null;

        if (r.isPresent()){
            r2.setUbicacion(reservaDTO.getUbicacion());
        } else throw new Excepciones("No hay reserva para actualizar", HttpStatus.NOT_FOUND);

    }


}
