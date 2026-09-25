package com.delivery.dto;

import com.delivery.model.Prato;
import java.math.BigDecimal;

public record PratoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        String categoria,
        Integer calorias,
        Double quantidade,
        String unidadeMedida
) {
    // Construtor para converter a Entidade Prato
    public PratoResponseDTO(Prato prato) {
        this(
                prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getValor(),
                prato.getCategoria(),
                prato.getCalorias(),
                prato.getQuantidade(),
                prato.getUnidadeMedida()
        );
    }
}