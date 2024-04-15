package com.itau.desafio.factory;

import com.itau.desafio.service.dto.UpdateNotificationsDto;
import com.itau.desafio.service.dto.UpdateTransferDto;
import com.itau.desafio.service.factory.UpdateNotificationsDtoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UpdateNotificationsDtoFactoryTest {

    private static final double VALOR_TRANSFERENCIA = 1000.0;
    private static final String ORIGEM_ID = "origem_id";
    private static final String DESTINO_ID = "destino_id";

    @Mock
    private UpdateTransferDto updateTransferDto;

    @InjectMocks
    private UpdateNotificationsDtoFactory updateNotificationsDtoFactory;

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void testCreate() {
        UUID id = UUID.randomUUID();
        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(id.toString(), UUID.randomUUID().toString(),
                UUID.randomUUID().toString());

        UpdateNotificationsDto result = updateNotificationsDtoFactory.create(updateTransferDto);

        assertEquals(updateTransferDto.getValor(), result.getValor());
        assertEquals(updateTransferDto.getConta().getIdOrigem(), result.getConta().getIdOrigem());
        assertEquals(updateTransferDto.getConta().getIdDestino(), result.getConta().getIdDestino());
    }
}
