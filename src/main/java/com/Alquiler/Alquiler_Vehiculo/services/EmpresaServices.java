package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.EmpresaDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Empresa;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOEmpresa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EmpresaServices implements IEmpresaServices<EmpresaDTO> {

    // Vamos a colocar una capa de persistencia de seguridad para acceder a los
    // atributos necesarios del objeto Empresa | Entidad

    @Autowired
    private IDAOEmpresa idaoEmpresa;

    // Se va a utilizar un mapper para poder transformar el modelo a un DTO
    // Data Transfer Object para transferir lo necesario

    @Autowired
    private ObjectMapper mapper;

    @Override
    public EmpresaDTO save(EmpresaDTO empresaDTO) {
        Empresa empresa = mapper.convertValue(empresaDTO, Empresa.class);
        idaoEmpresa.save(empresa);
        return  empresaDTO;
    }

    @Override
    public void delete(Long id) {
        idaoEmpresa.deleteById(id);
    }

    @Override
    public EmpresaDTO findById(Long id) {
        Optional<Empresa> empresa = idaoEmpresa.findById(id);
        EmpresaDTO empresaDTO = null;

        if(empresa.isPresent()) empresaDTO = mapper.convertValue(empresa, EmpresaDTO.class);

        return empresaDTO;
    }

    @Override
    public Set<EmpresaDTO> findAll() {
        List<Empresa> empresas = idaoEmpresa.findAll();
        Set<EmpresaDTO> empresaDTOS = new HashSet<>();

        for ( Empresa i : empresas) empresaDTOS.add(mapper.convertValue(i,EmpresaDTO.class));

        return empresaDTOS;
    }

    @Override
    public void deleteAll() { deleteAll(); }

}
