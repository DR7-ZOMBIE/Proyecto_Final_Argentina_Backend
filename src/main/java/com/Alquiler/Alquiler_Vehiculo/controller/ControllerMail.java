package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.model.MailEstructura;
import com.Alquiler.Alquiler_Vehiculo.services.MailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class ControllerMail {
    @Autowired
    private MailServices mailServices;

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
