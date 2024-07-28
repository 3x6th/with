package com.web3.with.util;

import com.web3.with.security.config.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Utility class for JWT token generation and validation.
 */
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final AppProperties appProperties;

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> authorities =
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();
        claims.put("authorities", authorities);
        Date date = new Date();
        Date expiration = new Date(date.getTime() + appProperties.getAuth().getDuration().toMillis());
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(date)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();
    }


    public String getSubject(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }


    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    public List<SimpleGrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        Object rawRoles = claims.get("authorities");

        if (rawRoles instanceof List<?> rawList) {
            List<String> roles = rawList.stream()
                    .filter(obj -> obj instanceof String)
                    .map(obj -> (String) obj)
                    .toList();

            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();
        }
        return Collections.emptyList();
    }

    public String resolveToken(HttpServletRequest req, String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

}
