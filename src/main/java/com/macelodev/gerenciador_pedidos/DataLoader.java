package com.macelodev.gerenciador_pedidos;

import com.macelodev.gerenciador_pedidos.model.Categoria;
import com.macelodev.gerenciador_pedidos.model.Pedido;
import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.repository.CategoriaRepository;
import com.macelodev.gerenciador_pedidos.repository.PedidoRepository;
import com.macelodev.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

public class DataLoader {
    @Bean
    CommandLineRunner initDatabase(ProdutoRepository produtoRepo,
                                   CategoriaRepository categoriaRepo,
                                   PedidoRepository pedidoRepo) {
        return args -> {
            Produto produto = new Produto("Teclado", 150.0);
            Categoria categoria = new Categoria("Eletrônicos");
            Pedido pedido = new Pedido(LocalDate.now());

            produtoRepo.save(produto);
            categoriaRepo.save(categoria);
            pedidoRepo.save(pedido);

            System.out.println("Dados inseridos com sucesso!");
        };
    }
}

