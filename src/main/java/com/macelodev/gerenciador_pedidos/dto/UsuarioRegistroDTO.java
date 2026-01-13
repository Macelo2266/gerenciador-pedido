package com.macelodev.gerenciador_pedidos.dto;

import com.macelodev.gerenciador_pedidos.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRegistroDTO(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotNull
        Perfil perfil) {


}
