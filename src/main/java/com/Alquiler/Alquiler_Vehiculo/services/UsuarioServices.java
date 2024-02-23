package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Usuario;
import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServices implements IUsuarioServices<UsuarioDTO>{

    @Autowired
    private IDAOUsuario idaoUsuario;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        idaoUsuario.save(usuario);
        return usuarioDTO;
    }

    @Override
    public void deleteById(Long id) { idaoUsuario.deleteById(id); }

    @Override
    public UsuarioDTO findById(Long id) {
        Optional<Usuario> usuario = idaoUsuario.findById(id);
        UsuarioDTO usuarioDTO =  null;

        if (usuario.isPresent()) usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);

        return usuarioDTO;
    }

    @Override
    public Set<UsuarioDTO> findAll() {
        List<Usuario> usuarios = idaoUsuario.findAll();
        Set<UsuarioDTO> usuariosDTO = new HashSet<>();

        for ( Usuario i : usuarios) usuariosDTO.add(mapper.convertValue(i , UsuarioDTO.class));

        return usuariosDTO;
    }

    @Override
    public void deleteAll() { idaoUsuario.deleteAll(); }

}
