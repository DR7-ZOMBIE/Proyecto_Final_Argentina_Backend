package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IUsuarioServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin (origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControllerMYSQLUsuario {

    // Llamar el servicio con los metodos necesarios
    @Autowired
    private IUsuarioServices<UsuarioDTO> usuarioServices;

    // Transformar un usuario DTO a un usuario normal
    @Autowired
    private ModelMapper mapper;

    // Listar todos los usuario

    /*
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
    */

    /*
    // Buscar un usuario
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list/{id}")
    public UsuarioDTO findUser(@RequestParam Long id){

        Optional<UsuarioDTO> usuario = Optional.ofNullable(usuarioServices.findById(id));

        if (usuario.isPresent()){
            return usuarioServices.findById(id);
        }else{
            throw new Excepciones("El usuario buscado no existe", HttpStatus.NOT_FOUND);
        }
    }
    */


    /*
    // Eliminar un usuario

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@RequestParam Long id){

        Optional<UsuarioDTO> usuario = Optional.ofNullable(usuarioServices.findById(id));

        if (usuario.isPresent()){
            usuarioServices.deleteById(id);
        }else{
            throw new Excepciones("El usuario no existe", HttpStatus.NOT_FOUND);
        }
    }
     */
    /*
    // Agregar un Usuario
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<UsuarioDTO> addUser(@RequestBody UsuarioDTO usuario){
        // Guardar el usuario
        UsuarioDTO usuarioGuardado = usuarioServices.save(usuario);
        return ResponseEntity.ok(usuarioGuardado);

    }
    */

    // Modificar un Usuario
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public void updateUser(@RequestBody UsuarioDTO usuario, @RequestParam Long id){

        Optional<UsuarioDTO> u = Optional.ofNullable(usuarioServices.findById(id));
        UsuarioDTO o = null;

        if (u.isPresent()){
            //o.setCedula(usuario.getCedula());
            o.setNombre(usuario.getNombre());
        }else{
            throw new Excepciones("No hay usuario para actualizar", HttpStatus.NOT_FOUND);
        }

    }

}
