package com.macelodev.gerenciador_pedidos.DTOs;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PedidoCadastroDTO(@NotNull
                                   Long usuarioId,

                                @NotNull
                                List<Long> produtosIds,

                                @NotNull
                                BigDecimal total) {
}
