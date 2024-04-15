package com.itau.desafio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.retry.annotation.EnableRetry;

@EnableFeignClients
@SpringBootApplication
@EnableRetry
@EnableRedisRepositories
@OpenAPIDefinition(info = @Info(title = "Transferência API", version = "1", description = "API para transferência entre contas"))
public class DesafioApplication {
	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
}
