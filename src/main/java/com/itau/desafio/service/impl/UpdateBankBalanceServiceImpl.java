package com.itau.desafio.service.impl;

import com.itau.desafio.gateway.factory.UpdateBankBalanceMapper;
import com.itau.desafio.gateway.AccountClient;
import com.itau.desafio.service.UpdateBankBalanceService;
import com.itau.desafio.service.dto.UpdateBankBalanceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateBankBalanceServiceImpl implements UpdateBankBalanceService {

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private UpdateBankBalanceMapper updateBankBalanceMapper;

    public void execute(UpdateBankBalanceDto updateBankBalanceDto) {
        accountClient.updateBankBalance(updateBankBalanceMapper.toUpdateBankBalanceRequest(updateBankBalanceDto));
    }
}
