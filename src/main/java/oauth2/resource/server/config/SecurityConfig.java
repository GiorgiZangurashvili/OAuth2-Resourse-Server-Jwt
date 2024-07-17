package oauth2.resource.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${jwksUri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                r -> r.jwt(
                        jwtConfigurer -> {
                            // Resource Server should know about this endpoint
                            // so that it can validate jwt signature
                            // as this endpoint exposes public keys of encryption
                            jwtConfigurer.jwkSetUri(jwksUri);
                            jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter());
                        }
                )
        );

        http
                .authorizeHttpRequests(
                        authorize -> authorize.anyRequest().authenticated()
                );
        return http.build();
    }
}
