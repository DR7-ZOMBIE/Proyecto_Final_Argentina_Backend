package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IVehiculoServices;
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
@RequestMapping ("/vehiculo")
@Tag(name = "Vehiculo")
@CrossOrigin (origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControllerMYSQLVehiculo {
    @Autowired
    private IVehiculoServices<VehiculoDTO> vehiculoServices; // Accediendo al servicio

    @Autowired
    private ModelMapper mapper; // Tranformacion de objetos DTO

    // Listar todos los vehiculos
    @Operation( summary = "Este método se emplea para listar todos los vehículos",
            description = "Esta es una prueba de la descrición")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<VehiculoDTO> listVehiculos(){

        Optional<Set<VehiculoDTO>> listVehiculos = Optional.ofNullable(vehiculoServices.findAll());

        if (listVehiculos.isPresent()){
            return vehiculoServices.findAll();
        }else{
            throw new Excepciones("No existen vehiculos" , HttpStatus.NOT_FOUND);
        }

    }

    // Buscar vehiculo especifico
    @Operation( summary = "Este método se emplea para buscar un vehículo específico")
    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehiculoDTO findById(@PathVariable Long id){
        Optional<VehiculoDTO> vehiculo = Optional.ofNullable(vehiculoServices.findbyId(id));

        if (vehiculo.isPresent()){
            return vehiculoServices.findbyId(id);
        }else{
            throw new Excepciones("El vehiculo no esta disponible", HttpStatus.NOT_FOUND);
        }

    }

    // Agregar vehiculo al sistema
    @Operation( summary = "Este método se emplea para agregar un vehículo al sistema")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VehiculoDTO> addVehiculo(@RequestBody VehiculoDTO vehiculoDTO){
        vehiculoServices.save(vehiculoDTO);
        return ResponseEntity.ok(vehiculoServices.save(vehiculoDTO));

    }

    // Elminar un vehiculo
    @Operation( summary = "Este método se emplea para eliminar un vehículo")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehiculo(@PathVariable Long id){

        Optional<VehiculoDTO> vehiculo = Optional.ofNullable(vehiculoServices.findbyId(id));

        if (vehiculo.isPresent()){
            vehiculoServices.deleteById(id);
        }else{
            throw new Excepciones("El vehiculo no esta disponible",HttpStatus.NOT_FOUND);
        }

    }

    // Actualizando un vehiculo
    @Operation( summary = "Este método se emplea para actualizar un vehículo")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateVehiculo(@PathVariable Long id, @RequestBody VehiculoDTO vehiculoDTO){

        Optional<VehiculoDTO> o = Optional.ofNullable(vehiculoServices.findbyId(id));
        VehiculoDTO v = null;

        if (o.isPresent()){
            v = vehiculoServices.findbyId(id);
            v.setColor(vehiculoDTO.getColor());
        }else{
            throw new Excepciones("El vehiculo no se puede actualizar", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar todos los vehiculos
    @Operation(summary = "Este método se emplea para eliminar todos los vehículos")
    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllVehiculos(){
        Optional<Set<VehiculoDTO>> vehiculos = Optional.ofNullable(vehiculoServices.findAll());

        for (VehiculoDTO v : vehiculos.get()){
            vehiculoServices.deleteById(v.getID());
        }
    }

}
