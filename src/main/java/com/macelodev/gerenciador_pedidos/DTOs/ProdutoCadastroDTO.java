package com.macelodev.gerenciador_pedidos.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoCadastroDTO(@NotBlank
                                       String nome,

                                 @NotNull
                                 BigDecimal preco,

                                 @NotNull
                                       Integer estoque,

                                 @NotNull
                                       Long categoriaId,

                                 @NotNull
                                       Long fornecedorId) {
}
