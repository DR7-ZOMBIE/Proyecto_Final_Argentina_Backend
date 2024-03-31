package com.Alquiler.Alquiler_Vehiculo.model.config;

import com.Alquiler.Alquiler_Vehiculo.model.user.Usuario;
import com.Alquiler.Alquiler_Vehiculo.register.IDAOUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private IDAOUsuario userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1 obtain header that contains jwt

        String authorizationHeader = request.getHeader("Authorization"); // Bearer token

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2 Obtain jwt token

        String jwt = authorizationHeader.split(" ")[1];

        // 3 Obtain Subject/username from jwt token

        String username = jwtService.extractUsername(jwt);

        // 4 Set authentication object inside our security context

        Usuario usuario = (Usuario) userRepository.findByUsername(username).get();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usuario, null, usuario.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5 Execute rest of the filters

        filterChain.doFilter(request, response);

    }
}
