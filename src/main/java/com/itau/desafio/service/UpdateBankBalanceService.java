package com.itau.desafio.service;

import com.itau.desafio.service.dto.UpdateBankBalanceDto;
import org.springframework.stereotype.Service;

@Service
public interface UpdateBankBalanceService {
    void execute(UpdateBankBalanceDto updateBankBalanceDto);
}
