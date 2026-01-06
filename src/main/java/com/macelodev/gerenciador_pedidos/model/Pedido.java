package com.macelodev.gerenciador_pedidos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data = LocalDate.now();

    private LocalDate dataEntrega;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private Set<Produto> produtos = new HashSet<>();

    public Pedido() {}

    public Pedido(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Long getId() { return id; }
    public LocalDate getData() { return data; }
    public LocalDate getDataEntrega() { return dataEntrega; }
    public Set<Produto> getProdutos() { return produtos; }

    public void setId(Long id) { this.id = id; }
    public void setData(LocalDate data) { this.data = data; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = new HashSet<>(produtos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

