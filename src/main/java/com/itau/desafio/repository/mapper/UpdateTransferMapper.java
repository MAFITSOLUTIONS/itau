package com.itau.desafio.repository.mapper;

import com.itau.desafio.repository.entity.TransferEntity;
import com.itau.desafio.service.dto.UpdateTransferDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpdateTransferMapper {
    @Mapping(source = "conta.idOrigem", target = "idOrigem")
    @Mapping(source = "conta.idDestino", target = "idDestino")
    TransferEntity toTransferEntity (UpdateTransferDto updateTransferDto);
}
