package com.Alquiler.Alquiler_Vehiculo.model.config;

import com.Alquiler.Alquiler_Vehiculo.model.user.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.security.Permissions;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/error").permitAll();

                    authConfig.requestMatchers("/v2/api-docs",
                            "/**",
                            "/api/v1/auth/**",
                            "/v2/api-docs",
                            "/v3/api-docs",
                            "/v3/api-docs/**",
                            "/configuration/ui",
                            "/swagger-resources",
                            "/swagger-resources/**",
                            "/configuration/security",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/docs/**",
                            "/swagger-ui/**").permitAll();

                    // Configuraciones para las reservas con autenticacion
                    authConfig.requestMatchers(HttpMethod.GET, "/reserva/list").hasAuthority(Permission.READ_ALL_RESERVAS.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/reserva/add").hasAuthority(Permission.SAVE_ONE_RESERVA.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/reserva/delete/{id}").hasAuthority(Permission.DELETE_ONE_RESERVA.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/reserva/update/{id}").hasAuthority(Permission.UPDATE_ONE_RESERVA.name());
                    authConfig.requestMatchers(HttpMethod.GET, "reserva/list/{id}").hasAuthority(Permission.READ_ONE_RESERVA.name());

                    // Configuraciones para los vehiculos con autenticacion
                    authConfig.requestMatchers(HttpMethod.GET, "/vehiculo/list").hasAuthority(Permission.READ_ALL_VEHICULOS.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/vehiculo/add").hasAuthority(Permission.SAVE_ONE_VEHICULO.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/vehiculo/delete/{id}").hasAuthority(Permission.DELETE_ONE_VEHICULO.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/vehiculo/update/{id}").hasAuthority(Permission.UPDATE_ONE_VEHICULO.name());
                    authConfig.requestMatchers(HttpMethod.GET, "vehiculo/list/{id}").hasAuthority(Permission.READ_ONE_VEHICULO.name());

                    // Configuraciones para los usuarios con autenticacion
                    authConfig.requestMatchers(HttpMethod.GET, "/user/list").hasAuthority(Permission.READ_ALL_USUARIOS.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/user/add").hasAuthority(Permission.SAVE_ONE_USUARIO.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/user/delete/{id}").hasAuthority(Permission.DELETE_ONE_USUARIO.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/user/update/{id}").hasAuthority(Permission.UPDATE_ONE_USUARIO.name());
                    authConfig.requestMatchers(HttpMethod.GET, "user/list/{id}").hasAuthority(Permission.READ_ONE_USUARIO.name());

                    // Configuraciones para las categorias con autenticacion
                    authConfig.requestMatchers(HttpMethod.GET, "/categoria/list").hasAuthority(Permission.READ_ALL_CATEGORY.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/categoria/add").hasAuthority(Permission.SAVE_ONE_CATEGORY.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/categoria/delete/{id}").hasAuthority(Permission.DELETE_ONE_CATEGORY.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/categoria/update/{id}").hasAuthority(Permission.UPDATE_ONE_CATEGORY.name());
                    authConfig.requestMatchers(HttpMethod.GET, "categoria/list/{id}").hasAuthority(Permission.READ_ONE_CATEGORY.name());

                });
        return http.build();
    }

    // Configuración de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}