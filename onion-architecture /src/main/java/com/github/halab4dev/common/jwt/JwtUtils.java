package com.github.halab4dev.common.jwt;

import com.github.halab4dev.domain.constant.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/*
 *
 * @author halab
 */
public class JwtUtils {

    private JwtUtils() {
    }

    public static String generateJwt(JwtData data, String secret, Date expiration) {
        SecretKey secretKey = createSecretKey(secret);
        Date jwtTime = new Date();
        return Jwts.builder()
            .claim(JwtData.USER_ID, data.getUserId())
            .claim(JwtData.ROLE, data.getRole())
            .expiration(expiration)
            .issuedAt(jwtTime)
            .signWith(secretKey)
//            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public static JwtData parseAccessToken(String jwsString, String secret) {
        try {
            return parseJwt(jwsString, secret);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Unauthorized");
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Access token expired");
        } catch (Exception ex) {
            throw new RuntimeException("Invalid access token");
        }
    }

    @SuppressWarnings("unchecked")
    private static JwtData parseJwt(String jwsString, String secret) {
        SecretKey secretKey = createSecretKey(secret);
        Jws<Claims> jws = Jwts.parser()
//            .decryptWith(secretKey)
            .verifyWith(secretKey)
//            .setSigningKey(secretKey)
            .build()
            .parseSignedClaims(jwsString);
//            .parseClaimsJws(jwsString);
        Claims claims = jws.getPayload();

        return JwtData.builder()
            .userId(claims.get(JwtData.USER_ID, String.class))
            .role(Role.valueOf(claims.get(JwtData.ROLE, String.class)))
            .build();
    }

    private static SecretKey createSecretKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
