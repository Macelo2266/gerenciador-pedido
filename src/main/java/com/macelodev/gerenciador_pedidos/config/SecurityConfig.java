package com.macelodev.gerenciador_pedidos.config;

import com.macelodev.gerenciador_pedidos.repository.UsuarioRepository;
import com.macelodev.gerenciador_pedidos.service.TokenFilter;
import com.macelodev.gerenciador_pedidos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http,
            TokenService tokenService,
            UsuarioRepository repository
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        // Simplificado: Todas as operações de escrita/deleção são ADMIN
                        .requestMatchers(HttpMethod.GET, "/fornecedores/**", "/produtos/**").hasAnyRole("ADMIN", "CLIENTE")
                        .requestMatchers("/fornecedores/**", "/produtos/**").hasRole("ADMIN")

                        .requestMatchers("/pedidos/**").authenticated()

                        // Bloqueia qualquer outro path não mapeado por padrão (Whitelist approach)
                        .anyRequest().denyAll()
                )
                .addFilterBefore(
                        new TokenFilter(tokenService, repository),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}