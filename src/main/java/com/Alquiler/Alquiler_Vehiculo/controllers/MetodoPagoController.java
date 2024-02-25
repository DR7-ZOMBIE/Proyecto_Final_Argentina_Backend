package com.Alquiler.Alquiler_Vehiculo.controllers;

import com.Alquiler.Alquiler_Vehiculo.dto.Metodo_PagoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IMetodo_PagoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private IMetodo_PagoServices<Metodo_PagoDTO> metodoPagoServices;

    @PostMapping
    public ResponseEntity<Metodo_PagoDTO> crearMetodoPago(@RequestBody Metodo_PagoDTO metodoPagoDTO) {
        Metodo_PagoDTO nuevoMetodoPago = metodoPagoServices.save(metodoPagoDTO);
        return new ResponseEntity<>(nuevoMetodoPago, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metodo_PagoDTO> obtenerMetodoPagoPorId(@PathVariable Long id) {
        Metodo_PagoDTO metodoPagoDTO = metodoPagoServices.findById(id);
        return metodoPagoDTO != null ?
                new ResponseEntity<>(metodoPagoDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Set<Metodo_PagoDTO>> obtenerTodosLosMetodosPago() {
        Set<Metodo_PagoDTO> metodosPago = metodoPagoServices.findAll();
        return new ResponseEntity<>(metodosPago, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Metodo_PagoDTO> actualizarMetodoPago(@PathVariable Long id, @RequestBody Metodo_PagoDTO metodoPagoDTO) {
        Metodo_PagoDTO metodoPagoActualizado = metodoPagoServices.save(metodoPagoDTO);
        return new ResponseEntity<>(metodoPagoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable Long id) {
        metodoPagoServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
