package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.DTOs.DataEntregaRequestDTO;
import com.macelodev.gerenciador_pedidos.DTOs.PedidoRequestDTO;
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
    public Pedido criar(@RequestBody PedidoRequestDTO dto) {
        return service.criar(dto);
    }

    // Listar pedidos
    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Marcar como entregue
    @PutMapping("/{id}/entregar")
    public void entregar(
            @PathVariable Long id,
            @RequestBody DataEntregaRequestDTO dto
    ) {
        service.marcarComoEntregue(id, dto);
    }
}

