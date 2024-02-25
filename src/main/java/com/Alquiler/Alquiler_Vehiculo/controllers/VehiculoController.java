package com.Alquiler.Alquiler_Vehiculo.controllers;

import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IVehiculoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoServices<VehiculoDTO> vehiculoServices;

    @PostMapping
    public ResponseEntity<VehiculoDTO> crearVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO nuevoVehiculo = vehiculoServices.save(vehiculoDTO);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerVehiculoPorId(@PathVariable Long id) {
        VehiculoDTO vehiculoDTO = vehiculoServices.findbyId(id);
        return vehiculoDTO != null ?
                new ResponseEntity<>(vehiculoDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Set<VehiculoDTO>> obtenerTodosLosVehiculos() {
        Set<VehiculoDTO> vehiculos = vehiculoServices.findAllTSet();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizarVehiculo(@PathVariable Long id, @RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO vehiculoActualizado = vehiculoServices.save(vehiculoDTO);
        return new ResponseEntity<>(vehiculoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        vehiculoServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
