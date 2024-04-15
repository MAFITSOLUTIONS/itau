package com.itau.desafio.repository.mapper;

import com.itau.desafio.modal.Client;
import com.itau.desafio.repository.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ClientToClientEntityMapper {
    ClientEntity toClientEntity (Client client);
}
