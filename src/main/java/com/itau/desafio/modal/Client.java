package com.itau.desafio.modal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private String id;
    private String nome;
    private String telefone;
    private String tipoPessoa;
}
