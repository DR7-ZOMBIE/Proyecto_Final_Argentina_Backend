package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import com.Alquiler.Alquiler_Vehiculo.controllers.UsuarioController;
import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IUsuarioServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioTest {

    @Mock
    private IUsuarioServices<UsuarioDTO> usuarioServices;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    public void testCrearUsuario() {
        // Crea un objeto UsuarioDTO de prueba
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        // ... Configura los datos del usuarioDTO

        // Simula el comportamiento del servicio de usuarios
        when(usuarioServices.save(usuarioDTO)).thenReturn(usuarioDTO);

        // Llama al método del controlador
        ResponseEntity<UsuarioDTO> response = usuarioController.crearUsuario(usuarioDTO);

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuarioDTO, response.getBody());
    }

    @Test
    public void testObtenerUsuarioPorId() {
        // Establece el ID de prueba
        Long id = 1L;

        // Crea un objeto UsuarioDTO de prueba
        UsuarioDTO usuarioDTO = new UsuarioDTO(); // Configura los datos esperados

        // Simula el comportamiento del servicio de usuarios
        when(usuarioServices.findById(id)).thenReturn(usuarioDTO);

        // Llama al método del controlador
        ResponseEntity<UsuarioDTO> response = usuarioController.obtenerUsuarioPorId(id);

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
    }

    @Test
    public void testObtenerUsuarioPorId_NotFound() {
        // Establece un ID de prueba que no existe
        Long id = 2L;

        // Simula que el servicio de usuarios no encuentra el usuario
        when(usuarioServices.findById(id)).thenReturn(null);

        // Llama al método del controlador
        ResponseEntity<UsuarioDTO> response = usuarioController.obtenerUsuarioPorId(id);

        // Verifica que se devuelve un 404 NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testObtenerTodosLosUsuarios() {
        // Crea un conjunto de UsuarioDTO de prueba
        Set<UsuarioDTO> usuarios = new HashSet<>(); // Configura los datos de los usuarios

        // Simula el comportamiento del servicio de usuarios
        when(usuarioServices.findAll()).thenReturn(usuarios);

        // Llama al método del controlador
        ResponseEntity<Set<UsuarioDTO>> response = usuarioController.obtenerTodosLosUsuarios();

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarios, response.getBody());
    }

    @Test
    public void testActualizarUsuario() {
        // Establece el ID de prueba
        Long id = 1L;

        // Crea un objeto UsuarioDTO con los datos actualizados
        UsuarioDTO usuarioDTO = new UsuarioDTO(); // Configura los datos actualizados

        // Simula el comportamiento del servicio de usuarios
        when(usuarioServices.save(usuarioDTO)).thenReturn(usuarioDTO);

        // Llama al método del controlador
        ResponseEntity<UsuarioDTO> response = usuarioController.actualizarUsuario(id, usuarioDTO);

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
    }

    @Test
    public void testEliminarUsuario() {
        // Establece el ID de prueba
        Long id = 1L;

        // Simula que el servicio de usuarios elimina el usuario
        doNothing().when(usuarioServices).deleteById(id);

        // Llama al método del controlador
        ResponseEntity<Void> response = usuarioController.eliminarUsuario(id);

        // Verifica que se devuelve un 204 NO CONTENT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}