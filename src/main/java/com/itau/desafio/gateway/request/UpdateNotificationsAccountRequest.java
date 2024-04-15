package com.itau.desafio.gateway.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateNotificationsAccountRequest {
    private UUID idOrigem;
    private UUID idDestino;
}
