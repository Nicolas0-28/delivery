package com.delivery.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PratoRequestDTO(
        @NotBlank(message = "O nome do prato é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @Size(max = 255, message = "A descrição não pode exceder 255 caracteres")
        String descricao,

        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor deve ser maior que zero")
        BigDecimal valor,

        @NotBlank(message = "A categoria é obrigatória")
        String categoria,

        @Min(value = 0, message = "As calorias não podem ser negativas")
        Integer calorias,

        @Positive(message = "A quantidade deve ser maior que zero")
        Double quantidade,

        String unidadeMedida
) {}