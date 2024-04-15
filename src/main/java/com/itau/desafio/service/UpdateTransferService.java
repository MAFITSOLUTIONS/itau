package com.itau.desafio.service;

import com.itau.desafio.controller.response.TransferResponse;
import com.itau.desafio.service.dto.UpdateTransferDto;
import org.springframework.stereotype.Service;

@Service
public interface UpdateTransferService {
    TransferResponse execute(UpdateTransferDto updateTransferDto);
}
