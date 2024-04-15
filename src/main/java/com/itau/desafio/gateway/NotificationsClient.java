package com.itau.desafio.gateway;

import com.itau.desafio.gateway.request.UpdateNotificationsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotificationsClient", url = "http://localhost:9090")
public interface NotificationsClient {
    @PutMapping("/notificacoes/")
    void updateNotifications(@RequestBody UpdateNotificationsRequest updateNotificationsRequest);
}
