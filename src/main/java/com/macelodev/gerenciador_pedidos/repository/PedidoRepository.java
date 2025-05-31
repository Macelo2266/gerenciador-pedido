package com.macelodev.gerenciador_pedidos.repository;

import com.macelodev.gerenciador_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataEntregaIsNull();

    List<Pedido> findByDataEntregaIsNotNull();

    Iterable<Object> findByDataAfter(LocalDate data);

    Iterable<Object> findByDataBefore(LocalDate data);

    Iterable<Object> findByDataBetween(LocalDate inicio, LocalDate fim);
}
