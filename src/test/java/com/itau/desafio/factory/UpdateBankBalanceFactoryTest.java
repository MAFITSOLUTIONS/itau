package com.itau.desafio.factory;

import com.itau.desafio.service.dto.UpdateBankBalanceDto;
import com.itau.desafio.service.dto.UpdateTransferDto;
import com.itau.desafio.service.factory.UpdateBankBalanceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class UpdateBankBalanceFactoryTest {

    @InjectMocks
    private UpdateBankBalanceFactory updateBankBalanceFactory;

    @Autowired
    private FactoryTests factoryTests;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        UUID id = UUID.randomUUID();
        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(id.toString(),  UUID.randomUUID().toString()
                , UUID.randomUUID().toString());

        UpdateBankBalanceDto result = updateBankBalanceFactory.create(updateTransferDto);

        assertEquals(updateTransferDto.getConta().getIdDestino(), result.getConta().getIdDestino());
        assertEquals(updateTransferDto.getConta().getIdOrigem(), result.getConta().getIdOrigem());
        assertEquals(updateTransferDto.getValor(), result.getValor());
    }
}

