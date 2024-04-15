package com.itau.desafio.service.impl;

import com.itau.desafio.modal.Client;
import com.itau.desafio.repository.ClientRepository;
import com.itau.desafio.repository.cache.ClientCache;
import com.itau.desafio.service.GetCacheClientByIdService;
import com.itau.desafio.service.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GetCacheClientByIdServiceImpl implements GetCacheClientByIdService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public Optional<Client> findById(String id) {
        Optional<ClientCache> clientCache = clientRepository.findById(id);
        return clientCache.map(cache -> clientMapper.create(cache));
    }

    public void save(ClientCache clientCache) {
        clientRepository.save(clientCache);
    }
}
