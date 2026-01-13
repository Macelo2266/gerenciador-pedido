package com.macelodev.gerenciador_pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(Long id,
                                String usuario,
                                List<String> produtos,
                                BigDecimal total,
                                LocalDateTime data) {
}
