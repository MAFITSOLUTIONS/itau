package com.itau.desafio.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBankBalanceDto {
    private BigDecimal valor;
    private UpdateBankBalanceAccountDto conta;
}
