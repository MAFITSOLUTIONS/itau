package com.itau.desafio.gateway.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UpdateBankBalanceRequest {
    private BigDecimal valor;
    private UpdateBankBalanceAccountRequest conta;
}
