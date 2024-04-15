package com.itau.desafio.service.mapper;

import com.itau.desafio.modal.Transfer;
import com.itau.desafio.service.dto.UpdateTransferDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    UpdateTransferDto toUpdateTransferDto (Transfer transfer);
}
