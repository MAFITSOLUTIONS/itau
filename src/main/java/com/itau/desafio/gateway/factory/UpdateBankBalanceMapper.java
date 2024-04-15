package com.itau.desafio.gateway.factory;

import com.itau.desafio.gateway.request.UpdateBankBalanceRequest;
import com.itau.desafio.service.dto.UpdateBankBalanceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateBankBalanceMapper {
    UpdateBankBalanceRequest toUpdateBankBalanceRequest (UpdateBankBalanceDto updateBankBalanceDto);
}
