package com.macelodev.gerenciador_pedidos.model;

import jakarta.persistence.*;

import javax.naming.Name;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany(mappedBy = "produtos")
    private Set<Pedido> pedidos = new HashSet<>();

    @Column(nullable = false)
    private BigDecimal preco;


    public Produto() {}

    public Produto(String nome, BigDecimal preco, Categoria categoria,Fornecedor fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public Produto(String teclado, double valor) {
        this.nome = teclado;
        this.preco = new BigDecimal(valor);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public Categoria getCategoria() { return categoria; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public Set<Pedido> getPedidos() { return pedidos; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
    public void setPedidos(Set<Pedido> pedidos) { this.pedidos = pedidos; }

    @Override
    public String toString() {
        return "Produto{id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                ", fornecedor=" + fornecedor +
                '}';
    }
}
