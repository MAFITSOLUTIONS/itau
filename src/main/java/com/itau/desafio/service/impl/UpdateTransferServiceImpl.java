package com.itau.desafio.service.impl;

import com.itau.desafio.controller.response.TransferResponse;
import com.itau.desafio.exceptions.ResourceNotFoundException;
import com.itau.desafio.modal.Account;
import com.itau.desafio.modal.Client;
import com.itau.desafio.repository.TransferRepositoryFacade;
import com.itau.desafio.repository.mapper.UpdateTransferMapper;
import com.itau.desafio.service.GetAcountByIdService;
import com.itau.desafio.service.GetCacheClientByIdService;
import com.itau.desafio.service.GetClientByIdService;
import com.itau.desafio.service.dto.UpdateTransferDto;
import com.itau.desafio.service.factory.UpdateBankBalanceFactory;
import com.itau.desafio.service.factory.UpdateNotificationsDtoFactory;
import com.itau.desafio.service.UpdateNotificationsService;
import com.itau.desafio.service.UpdateBankBalanceService;
import com.itau.desafio.service.UpdateTransferService;
import com.itau.desafio.service.mapper.ClientCacheMapper;
import com.itau.desafio.service.mapper.TransferResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.itau.desafio.constants.ConstantMessages.*;

@Service
@Slf4j
public class UpdateTransferServiceImpl implements UpdateTransferService {

    @Autowired
    private TransferRepositoryFacade transferRepositoryFacade;

    @Autowired
    private UpdateTransferMapper updateTransferMapper;

    @Autowired
    private TransferResponseMapper transferResponseMapper;

    @Autowired
    private ClientCacheMapper clientCacheMapper;

    @Autowired
    private UpdateNotificationsDtoFactory updateNotificationsDtoFactory;

    @Autowired
    private UpdateBankBalanceFactory updateBankBalanceFactory;

    @Autowired
    private GetClientByIdService getClientByIdService;

    @Autowired
    private GetAcountByIdService getAcountByIdService;

    @Autowired
    private UpdateBankBalanceService updateBankBalanceService;

    @Autowired
    private GetCacheClientByIdService getCacheClientByIdService;

    @Autowired
    private UpdateNotificationsService updateNotificationsService;

    public TransferResponse execute(UpdateTransferDto updateTransferDto) {
        log.info("Start request with clientId: {}", updateTransferDto.getIdCliente());

        Optional<Client> client = verifyClientCache(updateTransferDto.getIdCliente());
        validateClient(client);

        Account origemAccount = getAcountByIdService.execute(updateTransferDto.getConta().getIdOrigem().toString());
        validateOrigemAccount(origemAccount, updateTransferDto);

        Account targetAccount = getAcountByIdService.execute(updateTransferDto.getConta().getIdDestino().toString());
        validateTargetAccount(targetAccount);

        updateBankBalanceService.execute(updateBankBalanceFactory.create(updateTransferDto));

        TransferResponse transferResponse = transferResponseMapper.toTransferResponse(transferRepositoryFacade.save(
                updateTransferMapper.toTransferEntity(updateTransferDto)
                )
        );

        updateNotificationsService.execute(
                updateNotificationsDtoFactory.create(updateTransferDto)
        );

        return transferResponse;
    }

    private void validateOrigemAccount(Account origemAccount, UpdateTransferDto updateTransferDto) {
        if (!origemAccount.getAtivo()) {
            log.info("Conta de Origem inativa para o ClientId: {}", updateTransferDto.getIdCliente());
            throw new ResourceNotFoundException(ORIGEM_ACCOUNT_INACTIVE_MESSAGE);
        }

        if (origemAccount.getSaldo().compareTo(updateTransferDto.getValor()) < 0) {
            log.info("Saldo insuficiente para o ClientId: {}", updateTransferDto.getIdCliente());
            throw new ResourceNotFoundException(INSUFFICIENT_FUNDS_MESSAGE);
        }

        if (origemAccount.getLimiteDiario().doubleValue() <= 0 ||
                origemAccount.getLimiteDiario().compareTo(updateTransferDto.getValor()) < 0) {
            log.info("Limite Diário insuficiente para o ClientId: {}", updateTransferDto.getIdCliente());
            throw new ResourceNotFoundException(DIARY_LIMIT_INSUFFICIENT_MESSAGE);
        }
    }

    private void validateTargetAccount(Account origemAccount) {
        if (!origemAccount.getAtivo()) {
            log.info("Conta Destino Inativa para o ClientId: {}", origemAccount.getId());
            throw new ResourceNotFoundException(TARGET_ACCOUNT_INACTIVE_MESSAGE);
        }
    }

    private void validateClient(Optional<Client> client) {
        if (client.isEmpty()) {
            log.info(CLIENT_NOT_FOUND_MESSAGE);
            throw new ResourceNotFoundException(CLIENT_NOT_FOUND_MESSAGE);
        }
    }

    private Optional<Client> verifyClientCache (String id) {
        Optional<Client> clientCache = getCacheClientByIdService.findById(id);
        if (getCacheClientByIdService.findById(id).isEmpty()) {
            Optional<Client> clientGateway = getClientByIdService.execute(id);
            if (clientGateway.isPresent()) {
                getCacheClientByIdService.save(clientCacheMapper.create(clientGateway.get()));
            }
            return clientGateway;
        } else {
            return clientCache;
        }
    }
}
