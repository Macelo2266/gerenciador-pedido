package com.macelodev.gerenciador_pedidos.controller;

import com.macelodev.gerenciador_pedidos.DTOs.ProdutoCadastroDTO;
import com.macelodev.gerenciador_pedidos.DTOs.ProdutoResponseDTO;
import com.macelodev.gerenciador_pedidos.model.Produto;
import com.macelodev.gerenciador_pedidos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize; // Removido o ; extra
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProdutoResponseDTO salvar(
            @RequestBody @Valid ProdutoCadastroDTO dto
    ) {
        Produto produto = service.criar(dto);

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getCategoria().getNome(),
                produto.getFornecedor().getNome()
        );
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return service.listar().stream().map(produto ->
                new ProdutoResponseDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getEstoque(),
                        produto.getCategoria() != null
                                ? produto.getCategoria().getNome()
                                : null,
                        produto.getFornecedor() != null
                                ? produto.getFornecedor().getNome()
                                : null
                )
        ).toList();
    }

}
