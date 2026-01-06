package com.macelodev.gerenciador_pedidos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<>();



    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome.trim();
    }


    public Long getId() { return id; }
    public void setId(Long id)
    { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome)
    { this.nome = nome.trim(); }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        produto.setCategoria(this);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
        produto.setCategoria(null);
    }
    @Override
    public String toString() {
        return nome;

    }
}

