package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.DTOs.LoginDTO;
import com.macelodev.gerenciador_pedidos.DTOs.TokenDTO;
import com.macelodev.gerenciador_pedidos.model.Usuario;
import com.macelodev.gerenciador_pedidos.service.TokenService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO dto) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());


        Authentication authentication = authenticationManager.authenticate(authToken);


        String token = tokenService.gerarToken(authentication);

        return new TokenDTO(token);
    }
}


