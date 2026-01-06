package com.macelodev.gerenciador_pedidos.DTOs;

import jakarta.validation.constraints.NotBlank;

public record CategoriaCadastroDTO(@NotBlank
                                   String nome) {
}
