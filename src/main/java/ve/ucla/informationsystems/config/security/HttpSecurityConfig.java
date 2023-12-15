package ve.ucla.informationsystems.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ve.ucla.informationsystems.config.security.filter.JwtAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionConf -> sessionConf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig-> {

                    authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();
                    authReqConfig.requestMatchers("/error").permitAll();


                    authReqConfig.anyRequest().authenticated();
                })
                .build();
    }
}
