package com.Alquiler.Alquiler_Vehiculo.controller;


import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.EmpresaDTO;
import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Empresa;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import com.Alquiler.Alquiler_Vehiculo.services.IEmpresaServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empresa")
public class ControllerMYSQLEmpresa {

    // Acceder a los servivcios de la empresa [ Metodos ]
    @Autowired
    private IEmpresaServices<EmpresaDTO> empresaServices;

    // Un mapper po si debemos transformacion algun Objeto DTO a normal y viceverrsa
    @Autowired
    private ModelMapper mapper;

    // Validar la existencia de todas las empresas
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<EmpresaDTO> empresaList() throws Excepciones {

        Optional<Set<EmpresaDTO>> listaEmpresaDTO = Optional.ofNullable(empresaServices.findAll());

        if (listaEmpresaDTO.isPresent()) {
            return empresaServices.findAll();
        }else{
            throw new Excepciones("No hay informacion de las empresas",HttpStatus.NOT_FOUND);
        }
    }

    // Buscar una empresa especifica
    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmpresaDTO findById(@PathVariable Long id) throws Excepciones {
        Optional<EmpresaDTO> empresaDTO = Optional.ofNullable(empresaServices.findById(id));

        if (empresaDTO.isPresent()){
            return empresaServices.findById(id);
        }else{
            throw new Excepciones("La empresa solicitada no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Elminar un empresa en especifico
    @DeleteMapping("/list/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws Excepciones {
        Optional<EmpresaDTO> empresaDTO = Optional.ofNullable(empresaServices.findById(id));

        if (empresaDTO.isPresent()){
            empresaServices.delete(id);
        }else{
            throw new Excepciones("La empresa solicita no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Guardar una empresa en la base de datos
    @PostMapping("/add")
    public ResponseEntity<EmpresaDTO> save(@RequestBody EmpresaDTO empresaDTO) {
        empresaServices.save(empresaDTO);
        return ResponseEntity.ok(empresaDTO);
    }

    // Actualizar una empresa
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaDTO update(@PathVariable Long id , @RequestBody EmpresaDTO empresaDTO){
        Optional<EmpresaDTO> o = Optional.ofNullable(empresaServices.findById(id));
        EmpresaDTO e =  null;

        if (o.isPresent()){
            e = empresaServices.findById(id);
            e.setDireccion(empresaDTO.getDireccion());
            e.setEmail(empresaDTO.getEmail());
            e.setTelefono(empresaDTO.getTelefono());
            e.setRazon_Social(empresaDTO.getRazon_Social());
            e.setVehiculos(empresaDTO.getVehiculos());
        }else{
            throw new Excepciones(" La empresa no puedo actualizar su informacion" , HttpStatus.NOT_FOUND);
        }
        return empresaServices.save(e);
    }

}
