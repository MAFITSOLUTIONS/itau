package com.itau.desafio.modal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    private UUID id;
    private String idCliente;
    private BigDecimal valor;
    private TransferAccount conta;
}
