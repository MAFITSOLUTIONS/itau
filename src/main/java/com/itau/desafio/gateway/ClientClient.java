package com.itau.desafio.gateway;

import com.itau.desafio.modal.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ClientClient", url = "http://localhost:9090")
public interface ClientClient {
    @GetMapping("/clientes/{id}")
    Optional<Client> getClienteById(@PathVariable("id") String id);
}
