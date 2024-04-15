package com.itau.desafio.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.gateway.AccountClient;
import com.itau.desafio.modal.Account;
import com.itau.desafio.service.impl.GetAccountByIdServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class GetAccountByIdServiceImplTest {

    @Mock
    private AccountClient accountClient;

    @InjectMocks
    private GetAccountByIdServiceImpl getAccountByIdService;

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void whenExecuteIsCalledWithValidIdThenReturnAccount() {
        UUID id = UUID.randomUUID();
        Account expectedAccount = factoryTests.createAccount(id.toString());

        when(accountClient.getContaById(id)).thenReturn(expectedAccount);

        Account result = getAccountByIdService.execute(id.toString());

        assertEquals(expectedAccount, result);
        verify(accountClient).getContaById(id);
    }
}
