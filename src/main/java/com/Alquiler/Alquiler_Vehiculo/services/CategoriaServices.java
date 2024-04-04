package com.Alquiler.Alquiler_Vehiculo.services;

import com.Alquiler.Alquiler_Vehiculo.dto.CategoriaDTO;
import com.Alquiler.Alquiler_Vehiculo.model.Categoria;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOCategoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoriaServices implements ICategoriaServices<CategoriaDTO>{

    @Autowired
    private IDAOCategoria idaoCategoria;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper mapperModel;


    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapperModel.map(categoriaDTO, Categoria.class);
        Categoria c = idaoCategoria.save(categoria);
        return mapperModel.map(c, CategoriaDTO.class);
    }

    @Override
    public void deleteById(Long id) { idaoCategoria.deleteById(id); }

    @Override
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoria = idaoCategoria.findById(id);
        CategoriaDTO categoriaDTO = null;

        if (categoria.isPresent()) categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);

        return categoriaDTO;
    }

    @Override
    public Set<CategoriaDTO> findAll() {
        List<Categoria> categorias = idaoCategoria.findAll();
        Set<CategoriaDTO> categoriasDTO = new HashSet<>(); // Inicializar el conjunto

        for (Categoria i: categorias) {
            categoriasDTO.add(mapperModel.map(i, CategoriaDTO.class));
        }

        return categoriasDTO;
    }

    @Override
    public void deleteAll() { idaoCategoria.deleteAll();}

    @Override
    public CategoriaDTO findByTitulo(String titulo) {
        Optional<Categoria> categoria = idaoCategoria.findByTitulo(titulo);
        CategoriaDTO categoriaDTO = null;

        if (categoria.isPresent()) categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);

        return categoriaDTO;
    }

}
