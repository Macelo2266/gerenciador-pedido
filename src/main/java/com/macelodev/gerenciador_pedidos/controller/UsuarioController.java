package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.DTOs.UsuarioCadastroDTO;
import com.macelodev.gerenciador_pedidos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController (UsuarioService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody UsuarioCadastroDTO dto){
        service.cadastrar(dto);
        return ResponseEntity.status(201).build();
    }

}
