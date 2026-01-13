package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.dto.DataEntregaRequestDTO;
import com.macelodev.gerenciador_pedidos.dto.PedidoRequestDTO;
import com.macelodev.gerenciador_pedidos.model.Pedido;
import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.model.Usuario;
import com.macelodev.gerenciador_pedidos.repository.PedidoRepository;
import com.macelodev.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.security.access.AccessDeniedException;
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

    public Pedido criar(PedidoRequestDTO dto, Usuario usuarioLogado) {

        Set<Produto> produtos = new HashSet<>(
                produtoRepository.findAllById(dto.produtosIds())
        );

        if (produtos.isEmpty()) {
            throw new RuntimeException("Pedido deve conter ao menos um produto");
        }

        Pedido pedido = new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setProdutos(new ArrayList<>(produtos));
        pedido.setUsuario(usuarioLogado); // 🔥 associação importante

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar(Usuario usuarioLogado) {
        if (usuarioLogado.isAdmin()) {
            return pedidoRepository.findAllWithProdutos();
        }
        return pedidoRepository.findByUsuario(usuarioLogado);
    }

    public Pedido buscarPorId(Long id, Usuario usuarioLogado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (!usuarioLogado.isAdmin()
                && !pedido.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Acesso negado");
        }

        return pedido;
    }

    public void marcarComoEntregue(
            Long id,
            DataEntregaRequestDTO dto,
            Usuario usuarioLogado
    ) {
        Pedido pedido = buscarPorId(id, usuarioLogado);
        pedido.setDataEntrega(dto.dataEntrega());
        pedidoRepository.save(pedido);
    }
}

