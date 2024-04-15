package com.itau.desafio.service.factory;

import com.itau.desafio.service.dto.UpdateBankBalanceAccountDto;
import com.itau.desafio.service.dto.UpdateBankBalanceDto;
import com.itau.desafio.service.dto.UpdateTransferDto;
import org.springframework.stereotype.Component;

@Component
public class UpdateBankBalanceFactory {

    public UpdateBankBalanceDto create (UpdateTransferDto updateTransferDto) {
        UpdateBankBalanceDto updateBankBalanceDto = new UpdateBankBalanceDto();
        UpdateBankBalanceAccountDto updateBankBalanceAccountDto = new UpdateBankBalanceAccountDto();

        updateBankBalanceAccountDto.setIdDestino(updateTransferDto.getConta().getIdDestino());
        updateBankBalanceAccountDto.setIdOrigem(updateTransferDto.getConta().getIdOrigem());
        updateBankBalanceDto.setValor(updateTransferDto.getValor());
        updateBankBalanceDto.setConta(updateBankBalanceAccountDto);

        return updateBankBalanceDto;
    }
}
