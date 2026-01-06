package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.model.Fornecedor;
import com.macelodev.gerenciador_pedidos.service.FornecedorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    // Criar fornecedor (ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Fornecedor salvar(@RequestBody Fornecedor fornecedor) {
        return service.salvar(fornecedor);
    }

    // Listar fornecedores
    @GetMapping
    public List<Fornecedor> listar() {
        return service.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Fornecedor buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

}
