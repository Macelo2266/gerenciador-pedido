package com.macelodev.gerenciador_pedidos.DTOs;

import com.macelodev.gerenciador_pedidos.model.Perfil;

public record UsuarioResponseDTO(Long id,
                                 String email,
                                 Perfil perfil) {
}
