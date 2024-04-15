package com.itau.desafio.modal;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Account {
    private UUID id;
    private BigDecimal saldo;
    private Boolean ativo;
    private BigDecimal limiteDiario;
}
