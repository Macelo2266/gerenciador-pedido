package com.macelodev.gerenciador_pedidos.DTOs;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DataEntregaRequestDTO(
        @NotNull LocalDate dataEntrega
) {}

