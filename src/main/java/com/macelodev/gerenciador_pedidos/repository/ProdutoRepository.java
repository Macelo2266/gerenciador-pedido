package com.macelodev.gerenciador_pedidos.repository;

import com.macelodev.gerenciador_pedidos.model.Categoria;
import com.macelodev.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // 🔍 Busca
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // 📦 Categoria
    List<Produto> findByCategoria(Categoria categoria);
    long countByCategoria(Categoria categoria);

    // 💰 Preço
    List<Produto> findByPrecoGreaterThan(BigDecimal preco);
    List<Produto> findByPrecoLessThan(BigDecimal preco);

    // 📊 Ordenações
    List<Produto> findByCategoriaOrderByPrecoAsc(Categoria categoria);
    List<Produto> findByCategoriaOrderByPrecoDesc(Categoria categoria);

    // 🔎 Busca combinada
    List<Produto> findByPrecoLessThanOrNomeContainingIgnoreCase(
            BigDecimal preco,
            String termo
    );

    // ⭐ Destaques
    List<Produto> findTop3ByOrderByPrecoDesc();
    List<Produto> findTop5ByCategoriaOrderByPrecoAsc(Categoria categoria);
}
