package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
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
public class VehiculoServices implements IVehiculoServices<VehiculoDTO>{

    @Autowired
    private IDAOVehiculo idaoVehiculo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public VehiculoDTO save(VehiculoDTO vehiculoDTO) {

        Vehiculo vehiculo =  modelMapper.map(vehiculoDTO, Vehiculo.class);

        Vehiculo savedVehiculo = idaoVehiculo.save(vehiculo);

        return modelMapper.map(savedVehiculo, VehiculoDTO.class);

    }

    @Override
    public void deleteById(Long id) { idaoVehiculo.deleteById(id);}

    @Override
    public VehiculoDTO findbyId(Long id) {
        Optional<Vehiculo> vehicle = idaoVehiculo.findById(id);
        VehiculoDTO vehiculoDTO = null;

        if (vehicle.isPresent()) vehiculoDTO = mapper.convertValue(vehicle, VehiculoDTO.class);

        return vehiculoDTO;
    }

    @Override
    public Set<VehiculoDTO> findAll() {
        List<Vehiculo> vehiculos = idaoVehiculo.findAll();
        Set<VehiculoDTO> vehiculoDTOS = new HashSet<>();

        for (Vehiculo i: vehiculos) vehiculoDTOS.add(modelMapper.map(i, VehiculoDTO.class));

        return vehiculoDTOS;
    }

    @Override
    public void deleteAll() { idaoVehiculo.deleteAll(); }
}
