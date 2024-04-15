package com.itau.desafio.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBankBalanceAccountDto {
    private UUID idOrigem;
    private UUID idDestino;
}
