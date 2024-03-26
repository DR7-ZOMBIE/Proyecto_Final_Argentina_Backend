package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.controller.ControllerMYSQLUsuario;
import com.Alquiler.Alquiler_Vehiculo.controller.ControllerMYSQLVehiculo;
import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IUsuarioServices;
import com.Alquiler.Alquiler_Vehiculo.services.IVehiculoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Usuario_Test {

    @InjectMocks
    private ControllerMYSQLUsuario controller;
    @Mock
    private ControllerMYSQLUsuario controllerMock;
    @Mock
    private IUsuarioServices<UsuarioDTO> usuarioServicesMock;


    @Mock
    private ModelMapper modelMapperMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUsuarioDTO_GettersAndSetters() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setID(1L);
        usuarioDTO.setEmail("12345678");
        usuarioDTO.setNombre("Juan Pérez");

        assertEquals(1L, usuarioDTO.getID().longValue());
        assertEquals("12345678", usuarioDTO.getEmail());
        assertEquals("Juan Pérez", usuarioDTO.getNombre());
    }

    @Test
    public void testFindUser_UsuarioExistente_DevuelveUsuario() {
        Long idUsuario = 1L;
        UsuarioDTO usuarioEsperado = new UsuarioDTO();

        when(usuarioServicesMock.findById(idUsuario)).thenReturn(usuarioEsperado);

        UsuarioDTO usuarioActual = controller.findUser(idUsuario);

        assertEquals(usuarioEsperado, usuarioActual);
    }
    @Test
    public void testDeleteUser_UsuarioExistente_EliminaUsuario() {
        Long idUsuario = 1L;

        when(usuarioServicesMock.findById(idUsuario)).thenReturn(new UsuarioDTO()); // Simula que el usuario existe

        controller.deleteUser(idUsuario);

        verify(usuarioServicesMock).deleteById(idUsuario); // Verifica que se llama al método de eliminación
    }
    @Test
    public void testAddUser_UsuarioValido_DevuelveUsuarioGuardadoConStatusOK() {
        UsuarioDTO usuario = new UsuarioDTO(); // Create a sample user object

        UsuarioDTO usuarioGuardado = new UsuarioDTO(); // Mock the saved user (without setting ID)

        when(usuarioServicesMock.save(usuario)).thenReturn(usuarioGuardado); // Mock the save service

        ResponseEntity<UsuarioDTO> response = controller.addUser(usuario);

        // Assert that the response contains the saved user
        assertEquals(usuarioGuardado, response.getBody());

        // Assert that the status code is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
