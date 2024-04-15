package com.itau.desafio.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateTransferDto {
    private UUID id;
    private String idCliente;
    private BigDecimal valor;
    private UpdateTransferAccountDto conta;
}
