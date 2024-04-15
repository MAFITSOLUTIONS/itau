package com.itau.desafio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.desafio.controller.response.TransferResponse;
import com.itau.desafio.factory.FactoryTests;
import com.itau.desafio.modal.Transfer;
import com.itau.desafio.service.UpdateTransferService;
import com.itau.desafio.service.dto.UpdateTransferDto;
import com.itau.desafio.service.mapper.TransferMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

@SpringBootTest
public class DesafioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UpdateTransferService updateTransferService;

    @Mock
    private TransferMapper transferMapper;

    @InjectMocks
    private DesafioController desafioController;

    @Autowired
    private FactoryTests factoryTests;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(desafioController).build();
    }

    @Test
    public void testUpdateTransfer() throws Exception {
        UUID idTransfer = UUID.randomUUID();
        Transfer transfer = factoryTests.createTransfer(idTransfer.toString());
        TransferResponse expectedResponse = new TransferResponse(idTransfer.toString());

        UpdateTransferDto updateTransferDto = factoryTests.createUpdateTransferDto(
                idTransfer.toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );

        when(transferMapper.toUpdateTransferDto(any(Transfer.class))).thenReturn(updateTransferDto);
        when(updateTransferService.execute(updateTransferDto)).thenReturn(expectedResponse);

        String transferJson = objectMapper.writeValueAsString(transfer);
        String expectedJsonResponse = objectMapper.writeValueAsString(expectedResponse);

        // Perform and assert
        mockMvc.perform(post("/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transferJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse));
    }
}

