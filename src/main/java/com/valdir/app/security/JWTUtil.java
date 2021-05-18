package com.valdir.app.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Metodo que irá receber o email e realizar a geração do token
     * @param email
     * @return token
     */
    public String generateToken(String email) {
        return Jwts.builder()

                // Define o valor de assunto de reivindicações do JWT.
                // Um valor nulo removerá a propriedade das reivindicações.
                .setSubject(email)

                // Define o valor de expiração das reivindicações JWT
                // Um valor nulo removerá a propriedade das reivindicações (claims)
                // Um JWT obtido após esse carimbo de data/hora não deve ser usado
                .setExpiration(new Date(System.currentTimeMillis() + expiration))

                // Assina o JWT construído usando o algoritmo especificado
                // com a chave especificada, produzindo um JWS
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())

                // Compacta o corpo do JWT usando o CompressionCodec especificado
                // deixando a API mais performatica
                .compact();
    }
}
