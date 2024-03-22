package com.Alquiler.Alquiler_Vehiculo.model.config;

import com.Alquiler.Alquiler_Vehiculo.model.user.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
public class JwtService {

    @Value("${security.jwt.expiration.minutes}")
    private Long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(Usuario usuario, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_MINUTES * 60 * 1000); // Ajuste según tu configuración

        String jwt = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwt; // Devuelve el JWT generado
    }


    private Key generateKey() {

        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String jwt) {

        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJwt(jwt).getBody().getSubject();
    }


}
