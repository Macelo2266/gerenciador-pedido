package com.macelodev.gerenciador_pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome.trim();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public List<Produto> getProdutos() { return produtos; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome.trim(); }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        produto.setCategoria(this);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
        produto.setCategoria(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


