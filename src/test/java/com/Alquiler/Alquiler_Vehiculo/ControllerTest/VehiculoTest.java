package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import com.Alquiler.Alquiler_Vehiculo.controllers.VehiculoController;
import com.Alquiler.Alquiler_Vehiculo.dto.VehiculoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IVehiculoServices;
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
public class VehiculoTest {

    @Mock
    private IVehiculoServices<VehiculoDTO> vehiculoServices;

    @InjectMocks
    private VehiculoController vehiculoController;

    @Test
    public void testCrearVehiculo() {
        // Crea un objeto VehiculoDTO de prueba
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        // ... Configura los datos del vehiculoDTO

        // Simula el comportamiento del servicio de vehículos
        when(vehiculoServices.save(vehiculoDTO)).thenReturn(vehiculoDTO);

        // Llama al método del controlador
        ResponseEntity<VehiculoDTO> response = vehiculoController.crearVehiculo(vehiculoDTO);

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(vehiculoDTO, response.getBody());
    }

    // ... Implementa los tests para los demás métodos del controlador:
    @Test
    public void testObtenerVehiculoPorId() {
        // Establece el ID de prueba
        Long id = 1L;

        // Crea un objeto VehiculoDTO de prueba
        VehiculoDTO vehiculoDTO = new VehiculoDTO(); // Configura los datos esperados

        // Simula el comportamiento del servicio de vehículos
        when(vehiculoServices.findbyId(id)).thenReturn(vehiculoDTO);

        // Llama al método del controlador
        ResponseEntity<VehiculoDTO> response = vehiculoController.obtenerVehiculoPorId(id);

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehiculoDTO, response.getBody());
    }
    @Test
    public void testObtenerVehiculoPorId_NotFound() {
        // Establece un ID de prueba que no existe
        Long id = 2L;

        // Simula que el servicio de vehículos no encuentra el vehículo
        when(vehiculoServices.findbyId(id)).thenReturn(null);

        // Llama al método del controlador
        ResponseEntity<VehiculoDTO> response = vehiculoController.obtenerVehiculoPorId(id);

        // Verifica que se devuelve un 404 NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testObtenerTodosLosVehiculos() {
        // Crea un conjunto de VehiculoDTO de prueba
        Set<VehiculoDTO> vehiculos = new HashSet<>(); // Configura los datos de los vehiculos

        // Simula el comportamiento del servicio de vehículos
        when(vehiculoServices.findAllTSet()).thenReturn(vehiculos);

        // Llama al método del controlador
        ResponseEntity<Set<VehiculoDTO>> response = vehiculoController.obtenerTodosLosVehiculos();

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehiculos, response.getBody());
    }
    @Test
    public void testActualizarVehiculo() {
        // Establece el ID de prueba
        Long id = 1L;

        // Crea un objeto VehiculoDTO con los datos actualizados
        VehiculoDTO vehiculoDTO = new VehiculoDTO(); // Configura los datos actualizados

        // Simula el comportamiento del servicio de vehículos
        when(vehiculoServices.save(vehiculoDTO)).thenReturn(vehiculoDTO);

        // Llama al método del controlador
        ResponseEntity<VehiculoDTO> response = vehiculoController.actualizarVehiculo(id, vehiculoDTO);

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehiculoDTO, response.getBody());
    }
}
