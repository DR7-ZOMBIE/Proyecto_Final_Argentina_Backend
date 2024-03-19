package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface IUsuarioServices extends UserDetailsService {
    Usuario guardar(UsuarioDTO registroDTO);

    //UsuarioDTO save(T t); // Guardar a un usuario en el sistema

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void deleteById(Long id); // Elminar un usuario por id
    //T findById(Long id);  // Buscar un usuario especifico por su id
    //Set<T> findAll(); // Listar todos los usuarios
    void deleteAll(); // Elminar todos los usuarios

    public List<Usuario> ListarUsuarios();

}
