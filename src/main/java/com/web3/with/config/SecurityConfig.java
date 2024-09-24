package com.web3.with.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.web3.with.security.filters.JwtFilter;
import com.web3.with.security.oauth2.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.AppSecurityRs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Security configuration.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final DefaultOAuth2UserService oAuth2UserService;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final SimpleUrlAuthenticationSuccessHandler oAuth2AuthorizationSuccessHandler;
    private final SimpleUrlAuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                    .requestMatchers(
                            "/",
                            "/api/v1/**",
                            "/error",
                            "/favicon.ico",
                            "/*/**.png",
                            "/*/**.gif",
                            "/*/**.svg",
                            "/*/**.jpg",
                            "/*/**.html",
                            "/*/**.css",
                            "/*/**.js",
                            "/swagger-ui/**",
                            "/api-docs/**"
                    ).permitAll()
                    .requestMatchers("/auth", "/oauth2/**", "/register").permitAll()
                    .requestMatchers(
                            "**/private/**").authenticated()
                    .anyRequest().authenticated()
            )
            .sessionManagement(policy -> policy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                    .authenticationEntryPoint(unauthorizedEntryPoint()))
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .oauth2Login(oAuth2LoginConfigurer -> oAuth2LoginConfigurer
                    .authorizationEndpoint(endpoint -> endpoint.baseUri("/oauth2/authorize")
                                                               .authorizationRequestRepository(
                                                                       httpCookieOAuth2AuthorizationRequestRepository))
                    .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/**"))
                    .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                    .successHandler(oAuth2AuthorizationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @Lazy
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(createUnauthorizedJson());
        };
    }

    private String createUnauthorizedJson() throws JsonProcessingException {
        AppSecurityRs appError = new AppSecurityRs(
                HttpStatus.UNAUTHORIZED.toString(),
                "Unauthorized",
                ZonedDateTime.now()
        );
        var mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME)
        );
        mapper.registerModule(javaTimeModule);
        return mapper.writeValueAsString(appError);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}