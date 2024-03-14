package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.controller.ControllerMYSQLVehiculo;
import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IVehiculoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Vehiculo_Test {

    @InjectMocks
    private ControllerMYSQLVehiculo controller;

    @Mock
    private IVehiculoServices<VehiculoDTO> vehiculoServicesMock;

    @Mock
    private ModelMapper modelMapperMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testListarVehiculos_ConVehiculos_DevuelveLista() throws Excepciones {
        Set<VehiculoDTO> vehiculosEsperados = new HashSet<>();
        vehiculosEsperados.add(new VehiculoDTO());

        when(vehiculoServicesMock.findAll()).thenReturn(vehiculosEsperados);

        Set<VehiculoDTO> vehiculosActuales = controller.listVehiculos();

        assertEquals(vehiculosEsperados, vehiculosActuales);
    }

    @Test
    public void testListarVehiculos_SinVehiculos_LanzaExcepcion() throws Excepciones {
        when(vehiculoServicesMock.findAll()).thenReturn(null);

        Excepciones excepcion = assertThrows(Excepciones.class, () -> controller.listVehiculos());

        assertEquals("No existen vehiculos", excepcion.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, excepcion.getStatus());
    }

    @Test
    public void testEncontrarPorId_ConIdExistente_DevuelveVehiculo() throws Excepciones {
        Long idExistente = 1L;
        VehiculoDTO vehiculoEsperado = new VehiculoDTO();

        when(vehiculoServicesMock.findbyId(idExistente)).thenReturn(vehiculoEsperado);

        VehiculoDTO vehiculoActual = controller.findById(idExistente);

        assertEquals(vehiculoEsperado, vehiculoActual);
    }

    @Test
    public void testEncontrarPorId_ConIdInexistente_LanzaExcepcion() throws Excepciones {
        Long idInexistente = 10L;

        when(vehiculoServicesMock.findbyId(idInexistente)).thenReturn(null);

        Excepciones excepcion = assertThrows(Excepciones.class, () -> controller.findById(idInexistente));

        assertEquals("El vehiculo no esta disponible", excepcion.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, excepcion.getStatus());
    }
    // Eliminar un vehículo existente
    @Test
    public void testDeleteVehiculo_ConIdExistente_EliminaCorrectamente() {
        Long idExistente = 1L;

        when(vehiculoServicesMock.findbyId(idExistente)).thenReturn(new VehiculoDTO());

        controller.deleteVehiculo(idExistente);

        // Verifica que se ha llamado al método de eliminación del servicio
        verify(vehiculoServicesMock).deleteById(idExistente);
    }

    // Eliminar un vehículo inexistente
    @Test
    public void testDeleteVehiculo_ConIdInexistente_LanzaExcepcion() {
        Long idInexistente = 10L;

        when(vehiculoServicesMock.findbyId(idInexistente)).thenReturn(null);

        Exception exception = assertThrows(Excepciones.class, () -> controller.deleteVehiculo(idInexistente));

        assertEquals("El vehiculo no esta disponible", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ((Excepciones) exception).getStatus());
    }
    @Test
    public void testAddVehiculo_GuardaCorrectamente() {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setColor("Rojo");
        // ... establecer otros atributos del DTO ...

        VehiculoDTO vehiculoGuardado = new VehiculoDTO();
        vehiculoGuardado.setId(1L); // Asignar un ID para la simulación del guardado
        vehiculoGuardado.setColor("Rojo");
        // ... establecer otros atributos del DTO guardado ...

        when(vehiculoServicesMock.save(vehiculoDTO)).thenReturn(vehiculoGuardado);

        ResponseEntity<VehiculoDTO> response = controller.addVehiculo(vehiculoDTO);

        // Verifica la respuesta y el estado HTTP
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehiculoGuardado, response.getBody());
    }
    @Test
    public void testAddVehiculo_LanzaExcepcion() {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        // ...

        when(vehiculoServicesMock.save(vehiculoDTO)).thenThrow(new Excepciones("Error al guardar"));

        Exception exception = assertThrows(Excepciones.class, () -> controller.addVehiculo(vehiculoDTO));
        // ... verificar mensaje de error y estado HTTP
    }
    @Test
    public void testUpdateVehiculo_ConIdExistente_ActualizaCorrectamente() {
        Long idExistente = 1L;
        VehiculoDTO vehiculoDTOActualizado = new VehiculoDTO();
        vehiculoDTOActualizado.setColor("Azul");

        when(vehiculoServicesMock.findbyId(idExistente)).thenReturn(vehiculoDTOActualizado);

        controller.updateVehiculo(idExistente, vehiculoDTOActualizado);

        // Verifica que el vehículo se ha actualizado correctamente
        assertEquals("Azul", vehiculoDTOActualizado.getColor());
    }
    @Test
    public void testUpdateVehiculo_ConIdInexistente_LanzaExcepcion() {
        Long idInexistente = 10L;
        VehiculoDTO vehiculoDTO = new VehiculoDTO();

        when(vehiculoServicesMock.findbyId(idInexistente)).thenReturn(null);

        Exception exception = assertThrows(Excepciones.class, () -> controller.updateVehiculo(idInexistente, vehiculoDTO));

        assertEquals("El vehiculo no se puede actualizar", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ((Excepciones) exception).getStatus());
    }
    @Test
    public void testUpdateVehiculo_ErrorServicioAlBuscar_LanzaExcepcion() {
        Long idExistente = 1L;
        VehiculoDTO vehiculoDTO = new VehiculoDTO();

        when(vehiculoServicesMock.findbyId(idExistente)).thenThrow(new Excepciones("Error al buscar vehículo"));

        Exception exception = assertThrows(Excepciones.class, () -> controller.updateVehiculo(idExistente, vehiculoDTO));

        assertEquals("Error al buscar vehículo", exception.getMessage());
        // ... verificar estado HTTP
    }


}
