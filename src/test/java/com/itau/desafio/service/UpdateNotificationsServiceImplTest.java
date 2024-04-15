package com.itau.desafio.service;

import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.gateway.NotificationsClient;
import com.itau.desafio.gateway.factory.UpdateNotificationsMapper;
import com.itau.desafio.gateway.request.UpdateNotificationsRequest;
import com.itau.desafio.service.dto.UpdateNotificationsDto;
import com.itau.desafio.service.impl.UpdateNotificationsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UpdateNotificationsServiceImplTest {

    @Mock
    private NotificationsClient notificationsClient;

    @Mock
    private UpdateNotificationsMapper updateNotificationsMapper;

    @InjectMocks
    private UpdateNotificationsServiceImpl updateNotificationsService;

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void testExecute() {
        UpdateNotificationsDto updateNotificationsDto = factoryTests.createUpdateNotificationsDto();
        UpdateNotificationsRequest updateNotificationsRequest = factoryTests.createUpdateNotificationsRequest();

        when(updateNotificationsMapper.toUpdateNotificationsRequest(updateNotificationsDto))
                .thenReturn(updateNotificationsRequest);

        updateNotificationsService.execute(updateNotificationsDto);

        verify(notificationsClient, times(1)).updateNotifications(updateNotificationsRequest);
    }

    @Test
    public void testExecuteWithCircuitBreaker() {
        // Arrange
        UpdateNotificationsDto updateNotificationsDto = factoryTests.createUpdateNotificationsDto();
        UpdateNotificationsRequest updateNotificationsRequest = factoryTests.createUpdateNotificationsRequest();

        when(updateNotificationsMapper.toUpdateNotificationsRequest(updateNotificationsDto))
                .thenReturn(updateNotificationsRequest);

        updateNotificationsService.execute(updateNotificationsDto);

        verify(notificationsClient, times(1)).updateNotifications(updateNotificationsRequest);
    }

    public void fallback(UpdateNotificationsDto updateNotificationsDto, RuntimeException exception) {
        // Fallback method implementation, if needed
    }
}
