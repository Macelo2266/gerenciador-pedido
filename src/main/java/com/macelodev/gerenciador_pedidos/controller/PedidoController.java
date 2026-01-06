package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.model.Pedido;
import com.macelodev.gerenciador_pedidos.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    // Criar pedido
    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return service.salvar(pedido);
    }

    // Listar pedidos
    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

}
