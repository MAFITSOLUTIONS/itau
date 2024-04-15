package com.itau.desafio.service.mapper;

import com.itau.desafio.controller.response.TransferResponse;
import com.itau.desafio.repository.entity.TransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferResponseMapper {
    @Mapping(source = "id", target =  "id_transferencia")
    TransferResponse toTransferResponse (TransferEntity transferEntity);
}
