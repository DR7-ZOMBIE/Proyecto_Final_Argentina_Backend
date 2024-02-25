package com.Alquiler.Alquiler_Vehiculo.controllers;

import com.Alquiler.Alquiler_Vehiculo.dto.ReservaDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private IReservaServices<ReservaDTO> reservaServices;

    @PostMapping
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO nuevaReserva = reservaServices.save(reservaDTO);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerReservaPorId(@PathVariable Long id) {
        ReservaDTO reservaDTO = reservaServices.findById(id);
        return reservaDTO != null ?
                new ResponseEntity<>(reservaDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Set<ReservaDTO>> obtenerTodasLasReservas() {
        Set<ReservaDTO> reservas = reservaServices.findAll();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaActualizada = reservaServices.save(reservaDTO);
        return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
