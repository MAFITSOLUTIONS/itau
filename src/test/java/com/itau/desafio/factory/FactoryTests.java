package com.itau.desafio.factory;

import com.itau.desafio.gateway.request.UpdateBankBalanceAccountRequest;
import com.itau.desafio.gateway.request.UpdateBankBalanceRequest;
import com.itau.desafio.gateway.request.UpdateNotificationsAccountRequest;
import com.itau.desafio.gateway.request.UpdateNotificationsRequest;
import com.itau.desafio.modal.Account;
import com.itau.desafio.modal.Client;
import com.itau.desafio.modal.Transfer;
import com.itau.desafio.modal.TransferAccount;
import com.itau.desafio.repository.cache.ClientCache;
import com.itau.desafio.repository.entity.TransferEntity;
import com.itau.desafio.service.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FactoryTests {
    public Account createDailyLimitExceededAccount (String id) {
        return new Account(UUID.fromString(id), BigDecimal.valueOf(1000), true, BigDecimal.valueOf(1000));
    }

    public Account createInsuficientFundsAccount (String id) {
        return new Account(UUID.fromString(id), BigDecimal.valueOf(1000), true, BigDecimal.valueOf(2000));
    }

    public Account createInactiveAccount (String id) {
        return new Account(UUID.fromString(id), BigDecimal.valueOf(10000), false, BigDecimal.valueOf(2000));
    }

    public Account createAccount (String id) {
        return new Account(UUID.fromString(id), BigDecimal.valueOf(10000), true, BigDecimal.valueOf(2000));
    }

    public Client createClient (String id) {
        return new Client(id, "João de Freitas", "123456789", "Física");
    }

    public UpdateTransferDto createUpdateTransferDto (String idClient, String idOrigemAccount, String idTargetAccount) {
        UpdateTransferAccountDto updateTransferAccountDto = new UpdateTransferAccountDto(UUID.fromString(idOrigemAccount),
                UUID.fromString(idTargetAccount));
        return new UpdateTransferDto(UUID.fromString(idClient), UUID.randomUUID().toString(), BigDecimal.valueOf(1500),
                updateTransferAccountDto
        );
    }

    public ClientCache createClientCache (String id) {
        return new ClientCache(id, "João de Oliveira", "119919778787", "Física");
    }

    public UpdateBankBalanceDto createUpdateBankBalanceDto () {
        UUID idOrigem = UUID.randomUUID();
        UUID idDestino = UUID.randomUUID();
        UpdateBankBalanceAccountDto updateBankBalanceAccountDto = new UpdateBankBalanceAccountDto(
                idOrigem,
                idDestino
        );
        return new UpdateBankBalanceDto(BigDecimal.valueOf(5000), updateBankBalanceAccountDto);
    }

    public UpdateBankBalanceRequest createUpdateBankBalanceRequest () {
        UUID idOrigem = UUID.randomUUID();
        UUID idDestino = UUID.randomUUID();
        UpdateBankBalanceAccountRequest updateBankBalanceAccountRequest = new UpdateBankBalanceAccountRequest(
                idOrigem,
                idDestino
        );
        return new UpdateBankBalanceRequest(BigDecimal.valueOf(5000), updateBankBalanceAccountRequest);
    }

    public UpdateNotificationsDto createUpdateNotificationsDto () {
        UUID idOrigem = UUID.randomUUID();
        UUID idDestino = UUID.randomUUID();
        UpdateNotificationsAccountDto updateNotificationsAccountDto = new UpdateNotificationsAccountDto(
                idOrigem,
                idDestino
        );
        return new UpdateNotificationsDto(BigDecimal.valueOf(5000), updateNotificationsAccountDto);
    }

    public UpdateNotificationsRequest createUpdateNotificationsRequest () {
        UUID idOrigem = UUID.randomUUID();
        UUID idDestino = UUID.randomUUID();
        UpdateNotificationsAccountRequest updateNotificationsAccountRequest = new UpdateNotificationsAccountRequest(
                idOrigem,
                idDestino
        );
        return new UpdateNotificationsRequest(BigDecimal.valueOf(5000), updateNotificationsAccountRequest);
    }

    public TransferEntity createTransferEntity (String id) {
        return new TransferEntity(UUID.fromString(id),
                UUID.randomUUID().toString(), BigDecimal.valueOf(5000),
                UUID.randomUUID(),
                UUID.randomUUID()
        );
    }

    public Transfer createTransfer (String id) {
        UUID idTransfer = UUID.fromString(id);
        UUID idOrigemAccount = UUID.randomUUID();
        UUID idTargetAccount = UUID.randomUUID();
        TransferAccount transferAccount = new TransferAccount();
        transferAccount.setIdDestino(idTargetAccount);
        transferAccount.setIdOrigem(idOrigemAccount);
        return new Transfer(UUID.fromString(id),
                UUID.randomUUID().toString(),
                BigDecimal.valueOf(5000),
                transferAccount
        );
    }
}
