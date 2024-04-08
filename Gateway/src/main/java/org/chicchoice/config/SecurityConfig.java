package org.chicchoice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.authorizeExchange(exchanges->exchanges
                .pathMatchers(HttpMethod.POST, "/api/v1/users/login", "/api/v1/users/signup").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/v1/users","/api/v1/users/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.GET).authenticated()
                .pathMatchers("/api/v1/vetements/**").hasRole("USER")
                .pathMatchers("/api/v1/ensembles/**").hasRole("USER")
                .pathMatchers("/api/v1/couleurs/**").hasRole("ADMIN")
                .pathMatchers("/api/v1/media/**").hasRole("USER")
                .pathMatchers(HttpMethod.POST,"/api/v1/planfications/**").authenticated()
                .pathMatchers(HttpMethod.DELETE,"/api/v1/planfications/**").authenticated()
                .pathMatchers(HttpMethod.PUT,"/api/v1/planfications/**").authenticated()
                .pathMatchers("/api/v1/planfications/**").hasRole("USER")
        ).oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor(){
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}