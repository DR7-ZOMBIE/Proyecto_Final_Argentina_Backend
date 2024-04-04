package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.ReservaDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IReservaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/reserva")
@Tag( name = "Reserva")
@CrossOrigin (origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControllerMYSQLReserva {

    @Autowired
    private IReservaServices<ReservaDTO> reservaServices; // Instanciar todos los servicios de reservas

    @Autowired
    private ModelMapper mapper; // Tranformar un objeto DTO

    // Listar todas las reservas
    @Operation( summary = "Este método se emplea para listar todas las categorías")
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
    @Operation( summary = "Este método se emplea para buscar una reserva")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/{id}")
    public ReservaDTO findReserva(@PathVariable Long id){

        Optional<ReservaDTO> reserva = Optional.ofNullable(reservaServices.findById(id));

        if(reserva.isPresent()){
            return reservaServices.findById(id);
        }else{
            throw new Excepciones("La reserva no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una reserva
    @Operation( summary = "Este método se emplea para eliminar una reserva")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteReserva(@PathVariable Long id){

        Optional<ReservaDTO> reservaDTO = Optional.ofNullable(reservaServices.findById(id));

        if (!reservaDTO.isPresent()) throw new Excepciones("La reserva no existe", HttpStatus.NOT_FOUND);

        reservaServices.deleteById(id);
    }

    // Agregar una reserva
    @Operation ( summary = "Este método se emplea para agregar una reserva")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<ReservaDTO> addReserva(@RequestBody ReservaDTO reservaDTO){
        ReservaDTO r = reservaServices.save(reservaDTO);
        return ResponseEntity.ok(r);
    }

    // Modificar una reserva
    @Operation ( summary = "Este método se emplea para modificar una reserva" )
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public void updateReserva(@RequestBody ReservaDTO reservaDTO , @PathVariable Long id){

        Optional<ReservaDTO> r = Optional.ofNullable(reservaServices.findById(id));
        ReservaDTO r2 = new ReservaDTO(); // Inicializar r2 aquí

        if (r.isPresent()){
            r2 = r.get(); // Actualizar r2 con el valor de r si está presente
            r2.setUbicacion(reservaDTO.getUbicacion());
        } else throw new Excepciones("No hay reserva para actualizar", HttpStatus.NOT_FOUND);

    }

    // Eliminar todas las reservas
    @Operation( summary = "Este método se emplea para eliminar todas las categorías")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/all")
    public void deleteAllReservas(){
        reservaServices.deleteAll();
    }
}
