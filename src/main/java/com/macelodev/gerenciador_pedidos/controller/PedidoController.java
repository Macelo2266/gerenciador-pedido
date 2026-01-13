package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.dto.DataEntregaRequestDTO;
import com.macelodev.gerenciador_pedidos.dto.PedidoRequestDTO;
import com.macelodev.gerenciador_pedidos.model.Pedido;
import com.macelodev.gerenciador_pedidos.model.Usuario;
import com.macelodev.gerenciador_pedidos.service.PedidoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public Pedido criar(
            @RequestBody PedidoRequestDTO dto,
            @AuthenticationPrincipal Usuario usuario
    ) {
        return service.criar(dto, usuario);
    }


    @GetMapping
    public List<Pedido> listar(@AuthenticationPrincipal Usuario usuario) {
        return service.listar(usuario);
    }


    @GetMapping("/{id}")
    public Pedido buscarPorId(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario
    ) {
        return service.buscarPorId(id, usuario);
    }

    @PutMapping("/{id}/entregar")
    public void entregar(
            @PathVariable Long id,
            @RequestBody DataEntregaRequestDTO dto,
            @AuthenticationPrincipal Usuario usuario
    ) {
        service.marcarComoEntregue(id, dto, usuario);
    }
}
