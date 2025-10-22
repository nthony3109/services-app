package com.expensetracker.expenseapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class VerifyToken {
    @Value("${jwt.secrete}")
  private   String secretKey = "";
    //  Create signing key
    private Key getSignKey() {
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // Extract username (subject) from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract any claim from token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //  Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //  Extract userId from token
    public String extractUserId(String token) {
        Integer userId = extractClaim(token, claims -> claims.get("userId", Integer.class));
        return userId != null ? userId.toString():null;
    }

    //  Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate token (simple)
//    public boolean validateToken(String token) {
//        try {
//            extractAllClaims(token);
//            return !isTokenExpired(token);
//        } catch (Exception e) {
//            return false;
//        }
//    }
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);  // this verifies the signature
            boolean expired = isTokenExpired(token);
            if (expired) {
                System.out.println(" Token expired");
                return false;
            }
            System.out.println("Token valid!");
            return true;
        } catch (Exception e) {
            System.out.println(" Token validation error: " + e.getMessage());
            return false;
        }
    }

}
