package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.service.ProdutoService;
import org.springframework.security.access.prepost.PreAuthorize; // Removido o ; extra
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }
}
