package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.controller.ControllerMYSQLReserva;
import com.Alquiler.Alquiler_Vehiculo.dto.ReservaDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IReservaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Reserva_Test {
    @Mock
    private IReservaServices<ReservaDTO> reservaServices;

    @InjectMocks
    private ControllerMYSQLReserva controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListReservas() {
        Set<ReservaDTO> reservas = new HashSet<>();
        // Agregar reservas a 'reservas' según tus necesidades

        when(reservaServices.findAll()).thenReturn(reservas);

        Set<ReservaDTO> result = controller.listReservas();

        assertEquals(reservas, result);
    }

    @Test
    void testFindReserva() {
        Long id = 1L;
        ReservaDTO reserva = new ReservaDTO();
        // Configurar 'reserva' según tus necesidades

        when(reservaServices.findById(id)).thenReturn(reserva);

        ReservaDTO result = controller.findReserva(id);

        assertEquals(reserva, result);
    }

    @Test
    void testDeleteReserva() {
        Long id = 1L;
        ReservaDTO reserva = new ReservaDTO();
        // Configurar 'reserva' según tus necesidades

        when(reservaServices.findById(id)).thenReturn(reserva);

        controller.deleteReserva(id);

        verify(reservaServices, times(1)).deleteById(id);
    }

    @Test
    void testAddReserva() {
        ReservaDTO reserva = new ReservaDTO();
        // Configurar 'reserva' según tus necesidades

        ResponseEntity<ReservaDTO> expectedResponse = ResponseEntity.ok(reserva);

        ResponseEntity<ReservaDTO> result = controller.addReserva(reserva);

        assertEquals(expectedResponse, result);
    }
    @Test
    void testUpdateReserva_Success() {
        Long id = 1L;
        ReservaDTO existingReserva = new ReservaDTO();
        existingReserva.setId(id);
        existingReserva.setUbicacion("Antigua ubicación");

        ReservaDTO updatedReserva = new ReservaDTO();
        updatedReserva.setId(id);
        updatedReserva.setUbicacion("Nueva ubicación");

        when(reservaServices.findById(id)).thenReturn(existingReserva);
        when(reservaServices.save(any())).thenReturn(updatedReserva);

        controller.updateReserva(updatedReserva, id);

        verify(reservaServices, times(1)).findById(id);
        verify(reservaServices, times(1)).save(updatedReserva);
    }

    @Test
    void testUpdateReserva_NotFound() {
        Long id = 1L;
        ReservaDTO updatedReserva = new ReservaDTO();
        updatedReserva.setId(id);
        updatedReserva.setUbicacion("Nueva ubicación");

        when(reservaServices.findById(id)).thenReturn(null);

        assertThrows(Excepciones.class, () -> controller.updateReserva(updatedReserva, id));

        verify(reservaServices, times(1)).findById(id);
        verify(reservaServices, never()).save(updatedReserva);
    }
}

