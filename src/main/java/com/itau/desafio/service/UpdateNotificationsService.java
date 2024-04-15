package com.itau.desafio.service;

import com.itau.desafio.service.dto.UpdateNotificationsDto;
import org.springframework.stereotype.Service;

@Service
public interface UpdateNotificationsService {
    void execute(UpdateNotificationsDto updateNotificationsDto);
}
