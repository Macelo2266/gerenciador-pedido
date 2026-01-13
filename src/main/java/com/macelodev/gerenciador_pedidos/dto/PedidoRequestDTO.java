package com.macelodev.gerenciador_pedidos.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record PedidoRequestDTO(
        @NotEmpty List<Long> produtosIds
) {}
