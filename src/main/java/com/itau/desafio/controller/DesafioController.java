package com.itau.desafio.controller;

import com.itau.desafio.controller.response.TransferResponse;
import com.itau.desafio.modal.Transfer;
import com.itau.desafio.service.UpdateTransferService;
import com.itau.desafio.service.mapper.TransferMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/transferencia", produces = {"application/json"})
@Tag(name = "transferencia-api")
public class DesafioController {

    @Autowired
    private UpdateTransferService updateTransferService;

    @Autowired
    private TransferMapper transferMapper;

    @Operation(summary = "Realiza a transferência entre contas", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a transação."),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferResponse> updateTransfer(@RequestBody Transfer transfer) {
        TransferResponse response = updateTransferService.execute(transferMapper.toUpdateTransferDto(transfer));
        return ResponseEntity.ok(response);
    }
}
