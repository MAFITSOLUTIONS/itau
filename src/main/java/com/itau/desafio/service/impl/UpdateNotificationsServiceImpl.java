package com.itau.desafio.service.impl;

import com.itau.desafio.gateway.NotificationsClient;
import com.itau.desafio.gateway.factory.UpdateNotificationsMapper;
import com.itau.desafio.service.UpdateNotificationsService;
import com.itau.desafio.service.dto.UpdateNotificationsDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateNotificationsServiceImpl implements UpdateNotificationsService {

    @Autowired
    private NotificationsClient notificationsClient;

    @Autowired
    private UpdateNotificationsMapper updateNotificationsMapper;

    @CircuitBreaker(name = "notificacaoCircuitBreaker", fallbackMethod = "fallback")
    @Retry(name = "notificacaoRetry")
    public void execute(UpdateNotificationsDto updateNotificationsDto) {
        notificationsClient.updateNotifications(updateNotificationsMapper.toUpdateNotificationsRequest(updateNotificationsDto));
    }
}
