package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.model.Pedido;
import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.repository.PedidoRepository;
import com.macelodev.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

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

    public Pedido criar(List<Long> produtosIds) {

        List<Produto> produtos = produtoRepository.findAllById(produtosIds);

        Pedido pedido = new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setProdutos(produtos);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAllWithProdutos();
    }
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void marcarComoEntregue(Long id) {
        Pedido pedido = buscarPorId(id);
        pedido.setDataEntrega(LocalDate.now());
        pedidoRepository.save(pedido);
    }
}


