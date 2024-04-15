package com.itau.desafio.service;

import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.gateway.AccountClient;
import com.itau.desafio.gateway.factory.UpdateBankBalanceMapper;
import com.itau.desafio.gateway.request.UpdateBankBalanceRequest;
import com.itau.desafio.service.dto.UpdateBankBalanceDto;
import com.itau.desafio.service.impl.UpdateBankBalanceServiceImpl;
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
public class UpdateBankBalanceServiceImplTest {

    @Mock
    private AccountClient accountClient;

    @Mock
    private UpdateBankBalanceMapper updateBankBalanceMapper;

    @InjectMocks
    private UpdateBankBalanceServiceImpl updateBankBalanceService;

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void testExecute() {
        // Arrange
        UpdateBankBalanceDto updateBankBalanceDto = factoryTests.createUpdateBankBalanceDto();
        UpdateBankBalanceRequest updateBankBalanceRequest = factoryTests.createUpdateBankBalanceRequest();

        when(updateBankBalanceMapper.toUpdateBankBalanceRequest(updateBankBalanceDto))
                .thenReturn(updateBankBalanceRequest);

        updateBankBalanceService.execute(updateBankBalanceDto);

        verify(accountClient, times(1)).updateBankBalance(updateBankBalanceRequest);
    }
}
