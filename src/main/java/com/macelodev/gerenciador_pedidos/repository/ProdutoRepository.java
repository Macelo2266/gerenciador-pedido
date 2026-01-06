package com.macelodev.gerenciador_pedidos.repository;

import com.macelodev.gerenciador_pedidos.model.Categoria;
import com.macelodev.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByCategoria(Categoria categoria);
    long countByCategoria(Categoria categoria);

    List<Produto> findByPrecoGreaterThan(BigDecimal preco);
    long countByPrecoGreaterThan(BigDecimal preco);

    List<Produto> findByPrecoLessThan(BigDecimal preco);

    List<Produto> findByCategoriaOrderByPrecoAsc(Categoria categoria);
    List<Produto> findByCategoriaOrderByPrecoDesc(Categoria categoria);

    List<Produto> findByPrecoLessThanOrNomeContainingIgnoreCase(BigDecimal preco, String termo);

    List<Produto> findTop3ByOrderByPrecoDesc();
    List<Produto> findTop5ByCategoriaOrderByPrecoAsc(Categoria categoria);

}
