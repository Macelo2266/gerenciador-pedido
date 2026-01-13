package com.macelodev.gerenciador_pedidos.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaCadastroDTO(@NotBlank
                                   String nome) {
}
