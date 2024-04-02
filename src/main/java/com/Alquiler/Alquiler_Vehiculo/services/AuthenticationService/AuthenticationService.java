package com.Alquiler.Alquiler_Vehiculo.services.AuthenticationService;

import com.Alquiler.Alquiler_Vehiculo.model.config.JwtService;
import com.Alquiler.Alquiler_Vehiculo.model.user.Usuario;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IDAOUsuario userRepository;

    @Autowired
    private JwtService jwtservice;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse register(AuthenticationRequest request) {
        var usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        if (request.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        } else {
            throw new RuntimeException("La contraseña no puede ser nula");
        }
        usuario.setRole(request.getRole());

        // Aquí se asigna el apellido proveniente de la solicitud
        usuario.setApellido(request.getApellido());
        // Si tienes otros campos como 'nombre', 'email', etc., asegúrate de asignarlos aquí también
        usuario.setEmail(request.getEmail());
        usuario.setNombre(request.getNombre());

        userRepository.save(usuario);
        String token = jwtservice.generateToken(usuario, generateExtraClaims(usuario));

        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        );

        authenticationManager.authenticate(token);

        Usuario usuario = (Usuario) userRepository.findByUsername(request.getUsername()).get();

        String jwt = jwtservice.generateToken(usuario, generateExtraClaims(usuario));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(Usuario usuario) {

        Map<String, Object> extraClaims = new HashMap<String, Object>();

        extraClaims.put("name", usuario.getNombre());
        extraClaims.put("role", usuario.getRole().name());

        return extraClaims;

    }

}
