package com.fiap.gerenciamento_clientes.application.controller;

import com.fiap.gerenciamento_clientes.application.ports.AtualizarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.BuscarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.controller.dto.input.ClienteInput;
import com.fiap.gerenciamento_clientes.application.controller.dto.output.ClienteOutput;
import com.fiap.gerenciamento_clientes.application.ports.CadastrarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final CadastrarClienteUseCasePorts cadastrarClienteUseCasePorts;
    private final BuscarClienteUseCasePorts buscarClienteUseCasePorts;
    private final AtualizarClienteUseCasePorts atualizarClienteUseCasePorts;

    @PostMapping
    public ResponseEntity<ClienteOutput> cadastrar(@RequestBody ClienteInput clienteInput) {
        ClienteDTO clienteDTO = cadastrarClienteUseCasePorts.cadastrar(clienteInput.toDTO());
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(clienteDTO.toOutput());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutput> visualizarCliente(@PathVariable Long id) {
        ClienteDTO clienteDTO = buscarClienteUseCasePorts.buscarCliente(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(clienteDTO.toOutput());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutput> atualizarCliente(@PathVariable Long id, @RequestBody ClienteInput clienteInput) {
        ClienteDTO clienteDTO = atualizarClienteUseCasePorts.atualizarCliente(id, clienteInput.clienteInputToDTO());
        return  ResponseEntity.status(HttpStatusCode.valueOf(200)).body(clienteDTO.toOutput());
    }


}
