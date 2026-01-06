package com.macelodev.gerenciador_pedidos.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FornecedorCadastroDTO(
        @NotBlank
        String nome,

        @Email
        String email,

        String telefone) {
}
