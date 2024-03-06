package com.Alquiler.Alquiler_Vehiculo.controllers;

import com.Alquiler.Alquiler_Vehiculo.dto.EmpresaDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IEmpresaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.Set;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private DataSource dataSource;
    private IEmpresaServices<EmpresaDTO> empresaServices;

    @PostMapping
    public ResponseEntity<EmpresaDTO> crearEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO nuevaEmpresa = empresaServices.save(empresaDTO);
        return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> obtenerEmpresaPorId(@PathVariable Long id) {
        EmpresaDTO empresaDTO = empresaServices.findById(id);
        return empresaDTO != null ?
                new ResponseEntity<>(empresaDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Set<EmpresaDTO>> obtenerTodasLasEmpresas() {
        Set<EmpresaDTO> empresas = empresaServices.findAll();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> actualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO empresaActualizada = empresaServices.save(empresaDTO);
        return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
