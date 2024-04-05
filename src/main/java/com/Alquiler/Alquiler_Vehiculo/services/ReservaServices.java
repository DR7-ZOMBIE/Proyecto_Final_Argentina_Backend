package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.ReservaDTO;
import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.model.MetodoPago;
import com.Alquiler.Alquiler_Vehiculo.model.Reserva;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import com.Alquiler.Alquiler_Vehiculo.model.user.Usuario;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOMetodoPago;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOReserva;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOVehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservaServices implements IReservaServices<ReservaDTO>{

    @Autowired
    private IDAOReserva idaoReserva;

    @Autowired
    private IDAOVehiculo idaoVehiculo; // Suponiendo que este es tu repositorio para Vehiculo

    @Autowired
    private IDAOMetodoPago idaoMetodoPago; // Suponiendo que este es tu repositorio para MetodoPago

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReservaDTO save(ReservaDTO reservaDTO) {

        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        Reserva reservaSave = idaoReserva.save(reserva);
        return modelMapper.map(reservaSave, ReservaDTO.class);
    }



    @Override
    public void deleteById(Long id) { idaoReserva.deleteAll(); }

    @Override
    public ReservaDTO findById(Long id) {
        Optional<Reserva> reserva = idaoReserva.findById(id);
        ReservaDTO reservaDTO = null;

        if (reserva.isPresent()) reservaDTO = mapper.convertValue(reserva, ReservaDTO.class);

        return reservaDTO;
    }

    @Override
    public Set<ReservaDTO> findAll() {
        List<Reserva> reservas = idaoReserva.findAll();
        Set<ReservaDTO> reservasDTO = new HashSet<>();

        for ( Reserva i: reservas ) reservasDTO.add(modelMapper.map(i, ReservaDTO.class));

        return reservasDTO;
    }

    @Override
    public void deleteAll() { idaoReserva.deleteAll(); }

}
