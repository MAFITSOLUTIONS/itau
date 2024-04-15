package com.itau.desafio.service;

import com.itau.desafio.modal.Account;
import org.springframework.stereotype.Service;

@Service
public interface GetAcountByIdService {
    Account execute(String id);
}
