package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.model.Usuario;
import com.macelodev.gerenciador_pedidos.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // Criar usuário (ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    // Listar usuários (ADMIN)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listar() {
        return service.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
