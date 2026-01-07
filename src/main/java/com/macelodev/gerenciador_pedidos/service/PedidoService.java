package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.DTOs.DataEntregaRequestDTO;
import com.macelodev.gerenciador_pedidos.DTOs.PedidoRequestDTO;
import com.macelodev.gerenciador_pedidos.model.Pedido;
import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.repository.PedidoRepository;
import com.macelodev.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ProdutoRepository produtoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    // ✅ Criar pedido usando DTO
    public Pedido criar(PedidoRequestDTO dto) {

        Set<Produto> produtos = new HashSet<>(
                produtoRepository.findAllById(dto.produtosIds())
        );

        if (produtos.isEmpty()) {
            throw new RuntimeException("Pedido deve conter ao menos um produto");
        }

        Pedido pedido = new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setProdutos(new ArrayList<>(produtos));


        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAllWithProdutos();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    // ✅ Marcar como entregue usando DTO
    public void marcarComoEntregue(Long id, DataEntregaRequestDTO dto) {
        Pedido pedido = buscarPorId(id);
        pedido.setDataEntrega(dto.dataEntrega());
        pedidoRepository.save(pedido);
    }
}
