package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Usuario;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServices implements IUsuarioServices<UsuarioDTO>{


    @Autowired
    private IDAOUsuario idaoUsuario;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioServices(IDAOUsuario idaoUsuario){
        super();
        this.idaoUsuario = idaoUsuario;
    }


    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        Usuario usuarioSave = idaoUsuario.save(usuario);
        return modelMapper.map(usuarioSave, UsuarioDTO.class);
    }


    @Override
    public Usuario guardar(UsuarioDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(),
                registroDTO.getApellido(),registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()), registroDTO.getUserRol());
        return idaoUsuario.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = idaoUsuario.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o contraseña inválidos");
        }
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(usuario.getUserRol().name())
        );

        return new User(usuario.getEmail(),usuario.getPassword(), authorities);
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

    @Override
    public List<Usuario> ListarUsuarios() {
        return idaoUsuario.findAll();
    }


}
