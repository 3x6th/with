package com.web3.with.security.filters;

import com.web3.with.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final Map<String, String> detailsJwtMap = new HashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        checkAndSet(request);
        filterChain.doFilter(request, response);
        detailsJwtMap.clear();
    }

    private void checkAndSet(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        checkJwtToken(authHeader);
        setSecurityToken(request, detailsJwtMap.get("email"), detailsJwtMap.get("jwtToken"));
    }

    private void checkJwtToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var jwtToken = authHeader.replace("Bearer ", "");
            detailsJwtMap.put("jwtToken", jwtToken);
            try {
                detailsJwtMap.put("email", jwtUtil.getSubject(jwtToken));
            } catch (ExpiredJwtException | SignatureException | MalformedJwtException e) {
                log.error(e.getMessage());
            }
        }
    }

    private void setSecurityToken(HttpServletRequest request, String email, String jwtToken) {
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var roles = jwtUtil.getAuthoritiesFromToken(jwtToken);
            var token = new UsernamePasswordAuthenticationToken(email, null, roles);
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }
}
