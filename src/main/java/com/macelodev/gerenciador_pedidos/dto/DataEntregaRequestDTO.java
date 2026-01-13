package com.macelodev.gerenciador_pedidos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DataEntregaRequestDTO(
        @NotNull LocalDate dataEntrega
) {}

