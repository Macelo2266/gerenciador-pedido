package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.model.Categoria;
import com.macelodev.gerenciador_pedidos.service.CategoriaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // Criar categoria (ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Categoria salvar(@RequestBody Categoria categoria) {
        return service.salvar(categoria);
    }

    // Listar categorias
    @GetMapping
    public List<Categoria> listar() {
        return service.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
