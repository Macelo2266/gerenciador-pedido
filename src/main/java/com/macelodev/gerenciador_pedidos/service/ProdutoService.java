package com.macelodev.gerenciador_pedidos.service;

import com.macelodev.gerenciador_pedidos.DTOs.ProdutoCadastroDTO;
import com.macelodev.gerenciador_pedidos.model.Categoria;
import com.macelodev.gerenciador_pedidos.model.Fornecedor;
import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.repository.CategoriaRepository;
import com.macelodev.gerenciador_pedidos.repository.FornecedorRepository;
import com.macelodev.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository,
            FornecedorRepository fornecedorRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public Produto criar(ProdutoCadastroDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);

        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }
}
