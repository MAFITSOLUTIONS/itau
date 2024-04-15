package com.itau.desafio.service.impl;

import com.itau.desafio.gateway.ClientClient;
import com.itau.desafio.modal.Client;
import com.itau.desafio.service.GetClientByIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class GetClientByIdServiceImpl implements GetClientByIdService {

    @Autowired
    private ClientClient clientClient;

    public Optional<Client> execute(String id) {
        return clientClient.getClienteById(id);
    }
}
