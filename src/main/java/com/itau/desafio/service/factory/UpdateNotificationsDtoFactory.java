package com.itau.desafio.service.factory;

import com.itau.desafio.service.dto.UpdateNotificationsAccountDto;
import com.itau.desafio.service.dto.UpdateNotificationsDto;
import com.itau.desafio.service.dto.UpdateTransferDto;
import org.springframework.stereotype.Component;

@Component
public class UpdateNotificationsDtoFactory {
    public UpdateNotificationsDto create(UpdateTransferDto updateTransferDto) {
        UpdateNotificationsDto updateNotificationsDto = new UpdateNotificationsDto();
        UpdateNotificationsAccountDto updateNotificationsAccountDto = new UpdateNotificationsAccountDto();
        updateNotificationsAccountDto.setIdDestino(updateTransferDto.getConta().getIdDestino());
        updateNotificationsAccountDto.setIdOrigem(updateTransferDto.getConta().getIdOrigem());
        updateNotificationsDto.setValor(updateTransferDto.getValor());
        updateNotificationsDto.setConta(updateNotificationsAccountDto);
        return updateNotificationsDto;
    }

}
