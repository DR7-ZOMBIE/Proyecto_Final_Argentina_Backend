package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import com.Alquiler.Alquiler_Vehiculo.Excepciones;
import com.Alquiler.Alquiler_Vehiculo.controller.ControllerMYSQLMetodo_Pago;
import com.Alquiler.Alquiler_Vehiculo.dto.Metodo_PagoDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IMetodo_PagoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Metodo_Pago_Test {
    @InjectMocks
    private ControllerMYSQLMetodo_Pago controller;

    @Mock
    private IMetodo_PagoServices<Metodo_PagoDTO> servicePago;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMetodoList() {

        Set<Metodo_PagoDTO> mockMetodos = new HashSet<>();
        mockMetodos.add(new Metodo_PagoDTO(1L, true, 100.0, "Tarjeta de crédito", LocalDate.of(2024, 3, 11), 123, 456789, null));
        when(servicePago.findAll()).thenReturn(mockMetodos);
        Set<Metodo_PagoDTO> result = controller.metodoList();
        assertEquals(mockMetodos, result);
    }

    @Test
    void testFindBydId() {
        Long metodoId = 1L;
        Metodo_PagoDTO mockMetodo = new Metodo_PagoDTO(metodoId, false, 50.0, "PayPal", LocalDate.of(2024, 3, 12), 456, 987654, null);
        when(servicePago.findById(metodoId)).thenReturn(mockMetodo);
        Metodo_PagoDTO result = controller.findBydId(metodoId);
        assertEquals(mockMetodo, result);
    }

    @Test
    void testFindBydId_NotFound() {

        Long metodoId = 99L;
        when(servicePago.findById(metodoId)).thenReturn(null);
        assertThrows(Excepciones.class, () -> controller.findBydId(metodoId));
    }

    @Test
    void testAddPago() {

        Metodo_PagoDTO mockMetodo = new Metodo_PagoDTO(3L, true, 75.0, "Visa", LocalDate.of(2024, 3, 13), 789, 123456, null);
        when(servicePago.save(any(Metodo_PagoDTO.class))).thenReturn(mockMetodo);
        ResponseEntity<Metodo_PagoDTO> result = controller.addPago(mockMetodo);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockMetodo, result.getBody());
    }

    @Test
    void testDeletePago() {

        Long metodoId = 2L;
        Metodo_PagoDTO mockMetodo = new Metodo_PagoDTO(metodoId, true, 60.0, "MasterCard", LocalDate.of(2024, 3, 14), 987, 654321, null);
        when(servicePago.findById(metodoId)).thenReturn(mockMetodo);
        assertDoesNotThrow(() -> controller.deletePago(metodoId));
        verify(servicePago).deleById(metodoId);
    }

    @Test
    void testDeletePago_NotFound() {

        Long metodoId = 100L;
        when(servicePago.findById(metodoId)).thenReturn(null);
        assertThrows(Excepciones.class, () -> controller.deletePago(metodoId));
    }
    @Test
    void testUpdatePago() {

        Long metodoId = 1L;
        Metodo_PagoDTO mockMetodo = new Metodo_PagoDTO(metodoId, true, 100.0, "Tarjeta de crédito", null, 123, 456789, null);
        when(servicePago.findById(metodoId)).thenReturn(mockMetodo);
        assertDoesNotThrow(() -> {
            Metodo_PagoDTO updatedMetodo = new Metodo_PagoDTO();
            updatedMetodo.setMonto((double) 5.0);
            controller.updatePago(metodoId, updatedMetodo);
        });
        verify(servicePago).save(mockMetodo);
    }

    @Test
    void testUpdatePago_NotFound() {

        Long metodoId = 99L;
        when(servicePago.findById(metodoId)).thenReturn(null);
        assertThrows(Excepciones.class, () -> controller.updatePago(metodoId, new Metodo_PagoDTO()));
    }
}

