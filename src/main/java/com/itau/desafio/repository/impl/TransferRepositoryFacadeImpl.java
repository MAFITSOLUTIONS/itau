package com.itau.desafio.repository.impl;

import com.itau.desafio.repository.TransferRepositoryFacade;
import com.itau.desafio.repository.entity.TransferEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepositoryFacadeImpl implements TransferRepositoryFacade {

    @Autowired
    private TransferRepository transferRepository;

    public TransferEntity save(TransferEntity transfer) {
        return transferRepository.save(transfer);
    }

}
