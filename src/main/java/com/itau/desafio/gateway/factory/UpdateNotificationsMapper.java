package com.itau.desafio.gateway.factory;

import com.itau.desafio.gateway.request.UpdateNotificationsRequest;
import com.itau.desafio.service.dto.UpdateNotificationsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateNotificationsMapper {
    UpdateNotificationsRequest toUpdateNotificationsRequest (UpdateNotificationsDto updateNotificationsDto);
}
