package com.itau.desafio.repository.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Data
@AllArgsConstructor
public class ClientCache {
    @Id
    private String id;
    private String nome;
    private String telefone;
    private String tipoPessoa;
}
