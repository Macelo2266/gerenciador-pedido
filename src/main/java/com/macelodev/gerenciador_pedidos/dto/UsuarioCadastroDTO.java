package com.macelodev.gerenciador_pedidos.dto;

import com.macelodev.gerenciador_pedidos.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record UsuarioCadastroDTO(@Email
                                 @NotBlank
                                 String email,

                                 @NotBlank
                                 String senha,

                                 @NotNull
                                 Perfil perfil) {
}
