package com.Alquiler.Alquiler_Vehiculo.controller;

import com.Alquiler.Alquiler_Vehiculo.services.AuthenticationService.AuthenticationRequest;
import com.Alquiler.Alquiler_Vehiculo.services.AuthenticationService.AuthenticationResponse;
import com.Alquiler.Alquiler_Vehiculo.services.AuthenticationService.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Tag( name = "Auth")
public class ControllerAuthentication {

    private final AuthenticationService authenticationService;

    public ControllerAuthentication(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Registrar Usuario
    @Operation(summary = "Este método se emplea para realizar el registro de un usuario")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    // Autenticar Usuario
    @Operation(summary = "Este método se emplea para realizar autenticación de usuario")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

}
