package com.macelodev.gerenciador_pedidos.DTOs;

import com.macelodev.gerenciador_pedidos.model.Perfil;

public record UsuarioCadastroDTO(String email, String senha, Perfil perfil) {
}
