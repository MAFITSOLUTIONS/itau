package com.itau.desafio.service;

import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.gateway.ClientClient;
import com.itau.desafio.modal.Client;
import com.itau.desafio.service.impl.GetClientByIdServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetClientByIdServiceImplTest {

    @Mock
    private ClientClient clientClient;

    @InjectMocks
    private GetClientByIdServiceImpl getClientByIdService;

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void whenExecuteIsCalledWithValidIdThenReturnAccount() {
        UUID id = UUID.randomUUID();
        Client expectedClient = factoryTests.createClient(id.toString());

        when(clientClient.getClienteById(id.toString())).thenReturn(java.util.Optional.ofNullable(expectedClient));

        Optional<Client> result = getClientByIdService.execute(id.toString());

        assertEquals(expectedClient, result.get());
        verify(clientClient).getClienteById(id.toString());
    }
}
