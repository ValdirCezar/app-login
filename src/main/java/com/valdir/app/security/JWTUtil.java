package com.valdir.app.security;

import io.jsonwebtoken.Claims;
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

    // Metodo usado para verificar se o token passado como parâmetro é válido
    public boolean tokenValido(String token) {
          
        // Claims é um tipo do JWT que armazena as reinvindicações do token
        Claims claims = getClaims(token);
        if(claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    // Metodo usado para pegar as reinvindicações do token
    private Claims getClaims(String token) {
        try{
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // Metodo que irá pegar um usuário a partir do token
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if(claims != null) {
            return claims.getSubject();
        }
        return null;
    }

}
