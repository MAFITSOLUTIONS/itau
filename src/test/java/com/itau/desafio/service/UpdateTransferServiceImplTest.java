package com.itau.desafio.service;

import com.itau.desafio.controller.response.TransferResponse;
import com.itau.desafio.exceptions.ResourceNotFoundException;
import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.modal.Account;
import com.itau.desafio.modal.Client;
import com.itau.desafio.repository.TransferRepositoryFacade;
import com.itau.desafio.repository.entity.TransferEntity;
import com.itau.desafio.repository.mapper.UpdateTransferMapper;
import com.itau.desafio.service.dto.UpdateBankBalanceDto;
import com.itau.desafio.service.dto.UpdateNotificationsDto;
import com.itau.desafio.service.dto.UpdateTransferDto;
import com.itau.desafio.service.factory.UpdateBankBalanceFactory;
import com.itau.desafio.service.factory.UpdateNotificationsDtoFactory;
import com.itau.desafio.service.impl.UpdateTransferServiceImpl;
import com.itau.desafio.service.mapper.ClientCacheMapper;
import com.itau.desafio.service.mapper.TransferResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UpdateTransferServiceImplTest {

    @Mock
    private TransferRepositoryFacade transferRepositoryFacade;

    @Mock
    private UpdateTransferMapper updateTransferMapper;

    @Mock
    private TransferResponseMapper transferResponseMapper;

    @Mock
    private ClientCacheMapper clientCacheMapper;

    @Mock
    private UpdateNotificationsDtoFactory updateNotificationsDtoFactory;

    @Mock
    private UpdateBankBalanceFactory updateBankBalanceFactory;

    @Mock
    private GetClientByIdService getClientByIdService;

    @Mock
    private GetAcountByIdService getAccountByIdService;

    @Mock
    private UpdateBankBalanceService updateBankBalanceService;

    @Mock
    private GetCacheClientByIdService getCacheClientByIdService;

    @Mock
    private UpdateNotificationsService updateNotificationsService;

    @InjectMocks
    private UpdateTransferServiceImpl updateTransferService;

    @Autowired
    private FactoryTests factoryTests;

    @Test
    public void testExecute_SuccessfulTransfer() {
        UUID idClient = UUID.randomUUID();
        Optional<Client> client = Optional.of(factoryTests.createClient(idClient.toString()));

        UUID idTransfer = UUID.randomUUID();
        TransferEntity transferEntity = factoryTests.createTransferEntity(idTransfer.toString());

        UUID idOrigemAccount = UUID.randomUUID();
        Account origemAccount = factoryTests.createAccount(idOrigemAccount.toString());

        UUID idTargetAccount = UUID.randomUUID();
        Account targetAccount = factoryTests.createAccount(idTargetAccount.toString());

        UpdateBankBalanceDto updateBankBalanceDto = factoryTests.createUpdateBankBalanceDto();
        UpdateNotificationsDto updateNotificationsDto = factoryTests.createUpdateNotificationsDto();
        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(idTransfer.toString(),
                idOrigemAccount.toString(), idTargetAccount.toString());

        when(getCacheClientByIdService.findById(anyString())).thenReturn(client);
        when(transferRepositoryFacade.save(any())).thenReturn(transferEntity);
        when(getAccountByIdService.execute(idOrigemAccount.toString())).thenReturn(origemAccount);
        when(getAccountByIdService.execute(idTargetAccount.toString())).thenReturn(targetAccount);
        doNothing().when(updateNotificationsService).execute(any());

        when(updateTransferMapper.toTransferEntity(any())).thenReturn(transferEntity);
        when(updateBankBalanceFactory.create(any())).thenReturn(updateBankBalanceDto);
        when(updateNotificationsDtoFactory.create(any())).thenReturn(updateNotificationsDto);

        TransferResponse result = updateTransferService.execute(updateTransferDto);

        verify(updateNotificationsService, times(1)).execute(updateNotificationsDto);
    }

    @Test
    public void testExecute_ClientNotFound() {
        Optional<Client> client = Optional.empty();

        UUID id = UUID.randomUUID();
        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(id.toString(), UUID.randomUUID().toString()
                , UUID.randomUUID().toString());

        when(getCacheClientByIdService.findById(anyString())).thenReturn(client);
        when(getClientByIdService.execute(anyString())).thenReturn(client);

        assertThrows(ResourceNotFoundException.class, () -> updateTransferService.execute(updateTransferDto));
    }

    @Test
    public void testExecute_OriginAccountInactive() {
        UUID idClient = UUID.randomUUID();
        Optional<Client> client = Optional.of(factoryTests.createClient(idClient.toString())) ;

        UUID idTransfer = UUID.randomUUID();
        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(idTransfer.toString(), UUID.randomUUID().toString()
                , UUID.randomUUID().toString());

        UUID idAccount = UUID.randomUUID();
        Account account = factoryTests.createInactiveAccount(idAccount.toString());

        when(getCacheClientByIdService.findById(anyString())).thenReturn(client);
        when(getAccountByIdService.execute(anyString())).thenReturn(account);

        assertThrows(ResourceNotFoundException.class, () -> updateTransferService.execute(updateTransferDto));
    }

    @Test
    public void testExecute_InsufficientFunds() {
        UUID idClient = UUID.randomUUID();
        Optional<Client> client = Optional.of(factoryTests.createClient(idClient.toString())) ;

        UUID idTransfer = UUID.randomUUID();
        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(idTransfer.toString(), UUID.randomUUID().toString()
                , UUID.randomUUID().toString());

        UUID idAccount = UUID.randomUUID();
        Account account = factoryTests.createInsuficientFundsAccount(idAccount.toString());

        when(getCacheClientByIdService.findById(anyString())).thenReturn(client);
        when(getAccountByIdService.execute(anyString())).thenReturn(account);

        assertThrows(ResourceNotFoundException.class, () -> updateTransferService.execute(updateTransferDto));
    }

    @Test
    public void testExecute_OriginAccountDailyLimitExceeded() {
        UUID idClient = UUID.randomUUID();
        Optional<Client> client = Optional.of(factoryTests.createClient(idClient.toString())) ;

        UUID idTransfer = UUID.randomUUID();
        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(idTransfer.toString(), UUID.randomUUID().toString()
                , UUID.randomUUID().toString());

        UUID idAccount = UUID.randomUUID();
        Account account = factoryTests.createDailyLimitExceededAccount(idAccount.toString());

        when(getCacheClientByIdService.findById(anyString())).thenReturn(client);
        when(getAccountByIdService.execute(anyString())).thenReturn(account);

        assertThrows(ResourceNotFoundException.class, () -> updateTransferService.execute(updateTransferDto));
    }
}
