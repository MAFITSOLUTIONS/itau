package com.itau.desafio.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.repository.entity.TransferEntity;
import com.itau.desafio.repository.impl.TransferRepository;
import com.itau.desafio.repository.impl.TransferRepositoryFacadeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class TransferRepositoryFacadeImplTest {

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferRepositoryFacadeImpl transferRepositoryFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void testSaveTransfer() {
        UUID id = UUID.randomUUID();
        TransferEntity transfer = factoryTests.createTransferEntity(id.toString());

        when(transferRepository.save(any(TransferEntity.class))).thenReturn(transfer);

        TransferEntity savedTransfer = transferRepositoryFacade.save(transfer);

        verify(transferRepository).save(transfer);
        assertEquals(transfer.getId(), savedTransfer.getId());
    }
}
