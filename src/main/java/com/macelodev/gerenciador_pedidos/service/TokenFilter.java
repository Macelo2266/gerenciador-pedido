package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.repository.UsuarioRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class TokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository repository;

    public TokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Correção: Tratando o Optional com ifPresent para evitar erro de tipos incompatíveis
        recuperarToken(request).ifPresent(token -> {
            try {
                String email = tokenService.validarToken(token);

                repository.findByEmail(email).ifPresent(usuario -> {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    usuario,
                                    null,
                                    usuario.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            } catch (Exception e) {
                // Caso o token seja inválido ou expirado, o contexto de segurança não é definido
                logger.error("Erro ao validar token: " + e.getMessage());
            }
        });

        filterChain.doFilter(request, response);
    }

    private Optional<String> recuperarToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return Optional.of(bearer.substring(7));
        }
        return Optional.empty();
    }
}
