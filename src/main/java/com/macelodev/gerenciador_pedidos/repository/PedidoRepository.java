package com.macelodev.gerenciador_pedidos.repository;

import com.macelodev.gerenciador_pedidos.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataEntregaIsNull();
    List<Pedido> findByDataEntregaIsNotNull();

    List<Pedido> findByDataAfter(LocalDate data);
    List<Pedido> findByDataBefore(LocalDate data);
    List<Pedido> findByDataBetween(LocalDate inicio, LocalDate fim);

    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.produtos")
    List<Pedido> findAllWithProdutos();
}