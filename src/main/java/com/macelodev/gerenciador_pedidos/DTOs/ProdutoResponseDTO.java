package com.macelodev.gerenciador_pedidos.DTOs;


import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer quantidade,
        String categoria,
        String fornecedor
) {}