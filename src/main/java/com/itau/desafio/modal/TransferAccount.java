package com.itau.desafio.modal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TransferAccount {
    private UUID idOrigem;
    private UUID idDestino;
}
