package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import com.Alquiler.Alquiler_Vehiculo.controllers.ReservaController;
import com.Alquiler.Alquiler_Vehiculo.dto.ReservaDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IReservaServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservaTest {

    @Mock
    private IReservaServices<ReservaDTO> reservaServices;

    @InjectMocks
    private ReservaController reservaController;

    @Test
    public void testObtenerReservaPorId() {
        // Establece el ID de prueba
        Long id = 1L;

        // Crea un objeto ReservaDTO de prueba
        ReservaDTO reservaDTO = new ReservaDTO(); // Configura los datos esperados

        // Simula el comportamiento del servicio de reservas
        Mockito.when(reservaServices.findById(id)).thenReturn(reservaDTO);

        // Llama al método del controlador
        ResponseEntity<ReservaDTO> response = reservaController.obtenerReservaPorId(id);

        // Verifica la respuesta esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservaDTO, response.getBody());
    }

    @Test
    public void testObtenerReservaPorId_NotFound() {
        // Establece un ID de prueba que no existe
        Long id = 2L;

        // Simula que el servicio de reservas no encuentra la reserva
        Mockito.when(reservaServices.findById(id)).thenReturn(null);

        // Llama al método del controlador
        ResponseEntity<ReservaDTO> response = reservaController.obtenerReservaPorId(id);

        // Verifica que se devuelve un 404 NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testActualizarReserva() {
        // Establece el ID de prueba
        Long id = 1L;

        // Crea un objeto ReservaDTO con los datos actualizados
        ReservaDTO reservaDTO = new ReservaDTO(); // Configura los datos actualizados

        // Simula que el servicio de reservas guarda la reserva actualizada
        Mockito.when(reservaServices.save(reservaDTO)).thenReturn(reservaDTO);

        // Llama al método del controlador
        ResponseEntity<ReservaDTO> response = reservaController.actualizarReserva(id, reservaDTO);

        // Verifica que se devuelve un 200 OK y la reserva actualizada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservaDTO, response.getBody());
    }

    @Test
    public void testEliminarReserva() {
        // Establece el ID de prueba
        Long id = 1L;

        // Simula que el servicio de reservas elimina la reserva
        doNothing().when(reservaServices).deleteById(id);

        // Llama al método del controlador
        ResponseEntity<Void> response = reservaController.eliminarReserva(id);

        // Verifica que se devuelve un 204 NO CONTENT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
