package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.Metodo_PagoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IMetodo_PagoServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/pago")
public class ControllerMYSQLMetodo_Pago {

    @Autowired
    private IMetodo_PagoServices<Metodo_PagoDTO> servicePago; // Llamando lo servicios del metodo de pago
    @Autowired
    private ModelMapper mapper; // Transformando un objeto DTO

    // Listar todos los metodos de pago
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public Set<Metodo_PagoDTO> metodoList(){

        Optional<Set<Metodo_PagoDTO>> metodo_Pago = Optional.ofNullable(servicePago.findAll());

        if (metodo_Pago.isPresent()){
            return servicePago.findAll();
        }else{
            throw new Excepciones("No hay vehiculos disponibles", HttpStatus.NOT_FOUND);
        }
    }

    // Buscar un metodo de pago
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/{id}")
    public Metodo_PagoDTO findBydId(@PathVariable Long id){

        Optional<Metodo_PagoDTO> metodoPagoDTO = Optional.ofNullable(servicePago.findById(id));

        if (metodoPagoDTO.isPresent()){
            return servicePago.findById(id);
        }else{
            throw new Excepciones("El metodo de pago no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Guardar un metodo de pago
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<Metodo_PagoDTO> addPago(@RequestBody Metodo_PagoDTO metodoPagoDTO){
        return ResponseEntity.ok(servicePago.save(metodoPagoDTO));
    }

    // Eliminar un metodo de pago
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deletePago(@PathVariable Long id){

        Optional<Metodo_PagoDTO> metodoPagoDTO = Optional.ofNullable(servicePago.findById(id));

        if (metodoPagoDTO.isPresent()){
            servicePago.deleById(id);
        }else{
            throw new Excepciones("El metodo de pago no se puede eliminar");
        }
    }

    // Actualizar un metodo de pago
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePago(@PathVariable Long id, @RequestBody Metodo_PagoDTO metodoPagoDTO){

        Optional<Metodo_PagoDTO> m = Optional.ofNullable(servicePago.findById(id));
        Metodo_PagoDTO o = null;

        if (m.isPresent()){
            o.setMonto(metodoPagoDTO.getMonto());
        }else{
            throw new Excepciones("El metodo de pago no existe no se puede actualizar", HttpStatus.NOT_FOUND);
        }
    }


}
