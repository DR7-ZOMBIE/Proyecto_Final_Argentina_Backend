package com.Alquiler.Alquiler_Vehiculo.controller;


import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.dto.CategoriaDTO;
import com.Alquiler.Alquiler_Vehiculo.services.ICategoriaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControllerMYSQLCategoria {

    @Autowired
    private ICategoriaServices<CategoriaDTO> categoriaServices; // Instanciar los servicios de una categoria

    // Listar todas las categorias
    @Operation (summary = "Este metodo se usa para listar las categorías")
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


    // Buscar una categoria por id
    @Operation (summary = "Este metodo se usa para buscar una categoría por id")
    @RequestMapping("/list/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO findCategoriaID(@PathVariable Long id){
        Optional<CategoriaDTO> categoria = Optional.ofNullable(categoriaServices.findById(id));

        if(categoria.isPresent()){
            return categoriaServices.findById(id);
        }else{
            throw new Excepciones("La categoria no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Buscar una categoria por nombre
    @Operation (summary = "Este metodo se usa para buscar una categoría por titulo")
    @RequestMapping("/list/titulo/{titulo}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO findCategoriaNombre(@PathVariable String titulo){
        Optional<CategoriaDTO> categoria = Optional.ofNullable(categoriaServices.findByTitulo(titulo));

        if(categoria.isPresent()){
            return categoriaServices.findByTitulo(titulo);
        }else{
            throw new Excepciones("La categoria no existe", HttpStatus.NOT_FOUND);
        }
    }


    // Eliminar una categoria
    @Operation (summary = "Este metodo se usa para eliminar una categoría por Id")
    @RequestMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategoria(@PathVariable Long id) {
        Optional<CategoriaDTO> categoria = Optional.ofNullable(categoriaServices.findById(id));

        if (categoria.isPresent()) {
            categoriaServices.deleteById(id);
        }else{
            throw new Excepciones("La categoria no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Agregar una categoria
    @Operation (summary = "Este metodo se usa para crear una categoría")
    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO c = categoriaServices.save(categoriaDTO);
        return ResponseEntity.ok(c);
    }

    // Eliminar todas las categorias
    @Operation(summary = "Este metodo se usa para eliminar todas las categorías")
    @RequestMapping("/delete/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllCategoria(){
        categoriaServices.deleteAll();
    }

    // Modificar una categoria
    @Operation (summary = "Este método se usa para modificar una categoría")
    @RequestMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategoria(@RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id){

        Optional<CategoriaDTO> categoria = Optional.ofNullable(categoriaServices.findById(id));
        CategoriaDTO c = categoriaServices.findById(id);

        if (categoria.isPresent()){
            c.setDescripcion(categoriaDTO.getDescripcion());
            c.setImagen(categoriaDTO.getImagen());
            c.setTitulo(categoriaDTO.getTitulo());
            c.setVehiculos(categoriaDTO.getVehiculos());
            categoriaServices.save(c);
        }else{
            throw new Excepciones("La categoria no existe", HttpStatus.NOT_FOUND);
        }
    }
}
