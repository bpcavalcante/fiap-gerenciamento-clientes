package com.fiap.gerenciamento_clientes.application.controller;

import com.fiap.gerenciamento_clientes.application.controller.dto.input.ClienteInput;
import com.fiap.gerenciamento_clientes.application.controller.dto.output.ClienteOutput;
import com.fiap.gerenciamento_clientes.application.ports.CadastrarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final CadastrarClienteUseCasePorts cadastrarClienteUseCasePorts;

    @PostMapping
    public ResponseEntity<ClienteOutput> cadastrar(@RequestBody ClienteInput clienteInput) {
        ClienteDTO clienteDTO = cadastrarClienteUseCasePorts.cadastrar(clienteInput.toDTO());
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(clienteDTO.toOutput());
    }

}
