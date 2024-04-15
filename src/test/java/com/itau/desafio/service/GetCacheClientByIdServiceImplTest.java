package com.itau.desafio.service;

import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.modal.Client;
import com.itau.desafio.repository.ClientRepository;
import com.itau.desafio.repository.cache.ClientCache;
import com.itau.desafio.service.impl.GetCacheClientByIdServiceImpl;
import com.itau.desafio.service.mapper.ClientMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetCacheClientByIdServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private GetCacheClientByIdServiceImpl getCacheClientByIdService;

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void testFindById() {
        // Mocking the data
        UUID id = UUID.randomUUID();
        ClientCache clientCache = factoryTests.createClientCache(id.toString());
        Client client = factoryTests.createClient(id.toString());

        // Mocking repository behavior
        when(clientRepository.findById(id.toString())).thenReturn(Optional.of(clientCache));

        // Mocking mapper behavior
        when(clientMapper.create(clientCache)).thenReturn(client);

        // Calling the method under test
        Optional<Client> result = getCacheClientByIdService.findById(id.toString());

        // Assertions
        assertEquals(Optional.of(client), result);
    }

    @Test
    public void testFindById_NotFound() {
        String clientId = "123";

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        Optional<Client> result = getCacheClientByIdService.findById(clientId);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testSave() {
        UUID id = UUID.randomUUID();
        ClientCache clientCache = factoryTests.createClientCache(id.toString());
        when(clientRepository.findById(id.toString())).thenReturn(Optional.ofNullable(clientCache));
        getCacheClientByIdService.save(clientCache);
        verify(clientRepository).save(clientCache);
    }
}
