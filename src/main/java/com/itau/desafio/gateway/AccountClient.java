package com.itau.desafio.gateway;

import com.itau.desafio.gateway.request.UpdateBankBalanceRequest;
import com.itau.desafio.modal.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "AccountClient", url = "http://localhost:9090")
public interface AccountClient {
    @GetMapping("/contas/{id}")
    Account getContaById(@PathVariable("id") UUID id);

    @PutMapping("/contas/saldos")
    void updateBankBalance(@RequestBody UpdateBankBalanceRequest updateBankBalanceRequest);
}
