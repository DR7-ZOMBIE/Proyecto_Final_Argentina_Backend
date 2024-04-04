package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.MetodoPagoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IMetodoPagoServices;
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
@RequestMapping("/metodoPago")
@Tag(name = "MetodoPago")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControllerMYSQLMetodoPago {

    @Autowired
    private IMetodoPagoServices<MetodoPagoDTO> metodoPagoServices; // Instanciar los servicios de un metodo de pago

    @Autowired
    private ModelMapper mapper; // Tranformar un objeto DTO

    // Listar todos los metodos de pago
    @Operation( summary = "Este método se emplea para listar todos los métodos de pago")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<MetodoPagoDTO> listMetodoPago(){
        Optional<Set<MetodoPagoDTO>> pagos = Optional.ofNullable(metodoPagoServices.findAll());
        if (pagos.isPresent()){
            return metodoPagoServices.findAll();
        }else{
            throw new Excepciones("No hay metodos de pago disponibles", HttpStatus.NOT_FOUND);
        }
    }

    // Buscar un metodo de pago
    @Operation( summary = "Este método se emplea para buscar un método de pago por Id")
    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MetodoPagoDTO findMetodoPago(@PathVariable Long id){
        Optional<MetodoPagoDTO> pago = Optional.ofNullable(metodoPagoServices.findById(id));
        if(pago.isPresent()){
            return metodoPagoServices.findById(1L);
        }else{
            throw new Excepciones("El metodo de pago no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un metodo de pago
    @Operation( summary = "Este método se emplea para eliminar un método de pago")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMetodoPago(@PathVariable Long id) {
        Optional<MetodoPagoDTO> pago = Optional.ofNullable(metodoPagoServices.findById(id));

        if (pago.isPresent()) {
            metodoPagoServices.deleteById(id);
        }else{
            throw new Excepciones("El metodo de pago no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Agregar una metodo de pago
    @Operation( summary = "Este método se emplea para agregar un método de pago")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<MetodoPagoDTO> addReserva(@RequestBody MetodoPagoDTO metodoPagoDTO){
        MetodoPagoDTO m = metodoPagoServices.save(metodoPagoDTO);
        return ResponseEntity.ok(m);
    }

    // Eliminar todos los metodos de pago
    @Operation( summary = "Este método se emplea para eliminar todos los métodos de pago")
    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllMetodoPago(){
        metodoPagoServices.deleteAll();
    }

    // modificar un metodo de pago
    @Operation( summary = "Este método se emplea para modificar un método de pago")
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MetodoPagoDTO> updateMetodoPago(@RequestBody MetodoPagoDTO metodoPagoDTO, @PathVariable Long id){
        Optional<MetodoPagoDTO> pago = Optional.ofNullable(metodoPagoServices.findById(id));
        if (pago.isPresent()){
            metodoPagoServices.save(metodoPagoDTO);
            return ResponseEntity.ok(metodoPagoDTO);
        }else{
            throw new Excepciones("El metodo de pago no existe", HttpStatus.NOT_FOUND);
        }
    }

}
