package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.Metodo_PagoDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Metodo_Pago;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOMetodo_Pago;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class Metodo_PagoServices implements IMetodo_PagoServices<Metodo_PagoDTO>{

    @Autowired
    private IDAOMetodo_Pago idaoMetodo_pago;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Metodo_PagoDTO save(Metodo_PagoDTO metodoPagoDTO) {
        Metodo_Pago metodo_pago = modelMapper.map(metodoPagoDTO, Metodo_Pago.class);
        Metodo_Pago m = idaoMetodo_pago.save(metodo_pago);
        return modelMapper.map(m, Metodo_PagoDTO.class);
    }

    @Override
    public void deleById(Long id) { idaoMetodo_pago.deleteAll();}

    @Override
    public Metodo_PagoDTO findById(Long id) {
        Optional<Metodo_Pago> metodo_pago = idaoMetodo_pago.findById(id);
        Metodo_PagoDTO metodoPagoDTO = null;

        if (metodo_pago.isPresent()) metodoPagoDTO = mapper.convertValue(metodo_pago, Metodo_PagoDTO.class);

        return metodoPagoDTO;
    }

    @Override
    public Set<Metodo_PagoDTO> findAll() {
        List<Metodo_Pago> metodoPagoList = idaoMetodo_pago.findAll();
        Set<Metodo_PagoDTO> metodoPagoDTOS = new HashSet<>();

        for ( Metodo_Pago i : metodoPagoList) metodoPagoDTOS.add(mapper.convertValue(i,Metodo_PagoDTO.class));

        return metodoPagoDTOS;
    }

    @Override
    public void deleteAll() { idaoMetodo_pago.deleteAll(); }

}
