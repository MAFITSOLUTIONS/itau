package com.itau.desafio.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransferAccountDto {
    private UUID idOrigem;
    private UUID idDestino;
}
