package com.itau.desafio.service;

import com.itau.desafio.modal.Client;
import com.itau.desafio.repository.cache.ClientCache;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GetCacheClientByIdService {
    Optional<Client> findById(String id);
    void save(ClientCache clientCache);
}
