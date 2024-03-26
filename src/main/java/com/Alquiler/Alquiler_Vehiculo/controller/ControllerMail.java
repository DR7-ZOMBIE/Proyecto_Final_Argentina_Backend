package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.model.MailEstructura;
import com.Alquiler.Alquiler_Vehiculo.services.MailServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@Tag(name = "Mail")
public class ControllerMail {
    @Autowired
    private MailServices mailServices;

    @Operation (summary = "Este método se emplea para enviar un mail")
    @PostMapping("/send/{mail}")
    public ResponseEntity<String> sendMail(@PathVariable String mail, @RequestBody MailEstructura mailEstructura){
        try {
            mailServices.sendMail(mail, mailEstructura);
            return ResponseEntity.ok("Envío de correo electrónico exitoso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo electrónico: " + e.getMessage());
        }
    }
}
