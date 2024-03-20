package com.Alquiler.Alquiler_Vehiculo.controller;


import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.CategoriaDTO;
import com.Alquiler.Alquiler_Vehiculo.services.ICategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControllerMYSQLCategoria {

    @Autowired
    private ICategoriaServices<CategoriaDTO> categoriaServices; // Instanciar los servicios de una categoria

    // Listar todas las categorias
    @RequestMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<CategoriaDTO> listCategoria(){
        Optional<Set<CategoriaDTO>> categorias = Optional.ofNullable(categoriaServices.findAll());

        if (categorias.isPresent()){
            return categoriaServices.findAll();
        }else{
            throw new Excepciones("No hay categorias disponibles", HttpStatus.NOT_FOUND);
        }
    }


    // Buscar una categoria
    @RequestMapping("/list/id")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO findCategoria(@RequestParam Long id){
        Optional<CategoriaDTO> categoria = Optional.ofNullable(categoriaServices.findById(id));

        if(categoria.isPresent()){
            return categoriaServices.findById(id);
        }else{
            throw new Excepciones("La categoria no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una categoria
    @RequestMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategoria(@RequestParam Long id) {
        Optional<CategoriaDTO> categoria = Optional.ofNullable(categoriaServices.findById(id));

        if (categoria.isPresent()) {
            categoriaServices.deleteById(id);
        }else{
            throw new Excepciones("La categoria no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Agregar una categoria
    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody CategoriaDTO categoriaDTO){
        categoriaServices.save(categoriaDTO);
        return ResponseEntity.ok(categoriaDTO);
    }

    // Eliminar todas las categorias
    @RequestMapping("/delete/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllCategoria(){
        categoriaServices.deleteAll();
    }

    // Actualizar una categoria
    @RequestMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategoria(@RequestBody CategoriaDTO categoriaDTO){
        categoriaServices.save(categoriaDTO);
    }

    // Modificar una categoria
    @RequestMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategoria(@RequestBody CategoriaDTO categoriaDTO, @RequestParam Long id){
        Optional<CategoriaDTO> categoria = Optional.ofNullable(categoriaServices.findById(id));

        if (categoria.isPresent()){
            categoriaServices.save(categoriaDTO);
        }else{
            throw new Excepciones("La categoria no existe", HttpStatus.NOT_FOUND);
        }
    }
}
