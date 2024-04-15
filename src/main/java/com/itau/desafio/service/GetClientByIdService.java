package com.itau.desafio.service;

import com.itau.desafio.modal.Client;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GetClientByIdService {
    Optional<Client> execute(String id);
}
