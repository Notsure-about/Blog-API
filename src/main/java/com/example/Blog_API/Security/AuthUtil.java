package com.example.Blog_API.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.Blog_API.Entity.adminDetails;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {
    @Value("${jwt.secretKey}")
    private  String jwtSecretKey;
    private SecretKey getsecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateAccessToken(adminDetails details){
        return Jwts.builder()
                .subject(details.getUsername())
                .claim("adminDetailsID", details.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getsecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims =  Jwts.parser()
                .verifyWith(getsecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
