package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IUsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Tag( name = "User")
@CrossOrigin (origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControllerMYSQLUsuario {

    // Llamar el servicio con los metodos necesarios
    @Autowired
    private IUsuarioServices<UsuarioDTO> usuarioServices;

    // Transformar un usuario DTO a un usuario normal
    @Autowired
    private ModelMapper mapper;

    // Listar todos los usuario
    @Operation( summary = "Este método se emplea para listar todos los usuarios")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    private Set<UsuarioDTO> listAll(){

       Optional<Set<UsuarioDTO>> usuarios = Optional.ofNullable(usuarioServices.findAll());

       if (usuarios.isPresent()){
           return usuarioServices.findAll();
       }else{
           throw new Excepciones(" No hay usuariros en el sistema", HttpStatus.NOT_FOUND);
       }
    }

    // Buscar un usuario
    @Operation (summary = "Este método se emplea para busar un usuario")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/{id}")
    public UsuarioDTO findUser(@PathVariable Long id){

        Optional<UsuarioDTO> usuario = Optional.ofNullable(usuarioServices.findById(id));

        if (usuario.isPresent()){
            return usuarioServices.findById(id);
        }else{
            throw new Excepciones("El usuario buscado no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un usuario
    @Operation ( summary = "Este método se emplea para eliminar un usuario")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){

        Optional<UsuarioDTO> usuario = Optional.ofNullable(usuarioServices.findById(id));

        if (usuario.isPresent()){
            usuarioServices.deleteById(id);
        }else{
            throw new Excepciones("El usuario no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Agregar un Usuario
    @Operation( summary = "Este método se emplea para agragar un usuario")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<UsuarioDTO> addUser(@RequestBody UsuarioDTO usuario){
        // Guardar el usuario
        UsuarioDTO usuarioGuardado = usuarioServices.save(usuario);
        return ResponseEntity.ok(usuarioGuardado);

    }

    // Modificar un Usuario
    @Operation( summary = "Este método se emplea para modificar un usuario")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public void updateUser(@RequestBody UsuarioDTO usuario, @PathVariable Long id){

        Optional<UsuarioDTO> u = Optional.ofNullable(usuarioServices.findById(id));
        UsuarioDTO o = usuarioServices.findById(id);

        if (u.isPresent()){
            o.setEmail(usuario.getEmail());
            o.setNombre(usuario.getNombre());
            o.setApellido(usuario.getApellido());
            o.setRole(usuario.getRole());
            o.setPassword(usuario.getPassword());
            o.setEmail(usuario.getEmail());
            o.setReservas(usuario.getReservas());
            usuarioServices.save(o);

        }else{
            throw new Excepciones("No hay usuario para actualizar", HttpStatus.NOT_FOUND);
        }

    }

    // Eliminar todos los usuarios
    @Operation (summary = "Este método se emplea para eliminar todos los usuarios")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/all")
    public void deleteAll(){
        usuarioServices.deleteAll();
    }

}
