package com.itau.desafio.repository.entity;

import lombok.Data;

@Data
public class ClientEntity {
    private String id;
    private String nome;
    private String telefone;
    private String tipoPessoa;
}
