package com.itau.desafio.service.mapper;

import com.itau.desafio.modal.Client;
import com.itau.desafio.repository.cache.ClientCache;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {
    Client create (ClientCache client);
}
