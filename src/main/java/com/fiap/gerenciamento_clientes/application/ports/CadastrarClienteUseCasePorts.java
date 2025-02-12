package com.fiap.gerenciamento_clientes.application.ports;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;

public interface CadastrarClienteUseCasePorts {
    ClienteDTO cadastrar(ClienteDTO clienteDTO);
}
