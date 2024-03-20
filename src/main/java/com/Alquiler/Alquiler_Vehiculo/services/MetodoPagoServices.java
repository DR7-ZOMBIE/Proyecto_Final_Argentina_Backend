package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.MetodoPagoDTO;
import com.Alquiler.Alquiler_Vehiculo.model.MetodoPago;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOMetodoPago;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MetodoPagoServices implements IMetodoPagoServices<MetodoPagoDTO> {

    @Autowired
    private IDAOMetodoPago idaoMetodoPago;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public MetodoPagoDTO save(MetodoPagoDTO metodoPagoDTO) {
        MetodoPago metodoPago = modelMapper.map(metodoPagoDTO, MetodoPago.class);
        MetodoPago mp = idaoMetodoPago.save(metodoPago);
        return modelMapper.map(mp, MetodoPagoDTO.class);
    }

    @Override
    public void deleteById(Long id) { idaoMetodoPago.deleteById(id); }

    @Override
    public MetodoPagoDTO findById(Long id) {
        Optional<MetodoPago> metodoPago = idaoMetodoPago.findById(id);
        MetodoPagoDTO metodoPagoDTO = null;

        if (metodoPago.isPresent()) metodoPagoDTO = modelMapper.map(metodoPago, MetodoPagoDTO.class);

        return metodoPagoDTO;
    }

    @Override
    public Set<MetodoPagoDTO> findAll() {
        List<MetodoPago> metodosPago = idaoMetodoPago.findAll();
        Set<MetodoPagoDTO> metodosPagoDTO = null;
        for ( MetodoPago i: metodosPago ) metodosPagoDTO.add(modelMapper.map(i, MetodoPagoDTO.class));
        return metodosPagoDTO;
    }

    @Override
    public void deleteAll() { idaoMetodoPago.deleteAll(); }

}
