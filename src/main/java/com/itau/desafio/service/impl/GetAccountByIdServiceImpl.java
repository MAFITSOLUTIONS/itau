package com.itau.desafio.service.impl;

import com.itau.desafio.gateway.AccountClient;
import com.itau.desafio.modal.Account;
import com.itau.desafio.service.GetAcountByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetAccountByIdServiceImpl implements GetAcountByIdService {

    private final AccountClient accountClient;

    @Autowired
    public GetAccountByIdServiceImpl(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    public Account execute(String id) {
        return accountClient.getContaById(UUID.fromString(id));
    }
}
