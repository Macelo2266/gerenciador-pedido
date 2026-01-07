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
                        // 1. ROTAS PÚBLICAS
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Cadastro inicial

                        // 2. FORNECEDORES (Somente ADMIN pode cadastrar/editar/deletar)
                        .requestMatchers(HttpMethod.POST, "/fornecedores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/fornecedores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/fornecedores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/fornecedores/**").hasAnyRole("ADMIN", "CLIENTE")

                        // 3. PRODUTOS (Somente ADMIN cadastra/edita, mas todos podem ver)
                        .requestMatchers(HttpMethod.POST, "/produtos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/produtos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/produtos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/produtos/**").hasAnyRole("ADMIN", "CLIENTE")

                        // 4. PEDIDOS (Qualquer usuário logado pode acessar)
                        .requestMatchers("/pedidos/**").authenticated()

                        // 5. QUALQUER OUTRA REQUISIÇÃO
                        .anyRequest().authenticated()
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