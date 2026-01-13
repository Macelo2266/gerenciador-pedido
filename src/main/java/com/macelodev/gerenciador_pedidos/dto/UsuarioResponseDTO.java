package com.macelodev.gerenciador_pedidos.dto;

import com.macelodev.gerenciador_pedidos.model.Perfil;

public record UsuarioResponseDTO(Long id,
                                 String email,
                                 Perfil perfil) {
}
