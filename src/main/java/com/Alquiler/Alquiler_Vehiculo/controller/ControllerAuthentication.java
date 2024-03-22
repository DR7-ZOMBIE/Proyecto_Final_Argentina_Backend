package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.services.AuthenticationService.AuthenticationRequest;
import com.Alquiler.Alquiler_Vehiculo.services.AuthenticationService.AuthenticationResponse;
import com.Alquiler.Alquiler_Vehiculo.services.AuthenticationService.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class ControllerAuthentication {

    private final AuthenticationService authenticationService;

    public ControllerAuthentication(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Registrar Usuario
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    // Autenticar Usuario
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
