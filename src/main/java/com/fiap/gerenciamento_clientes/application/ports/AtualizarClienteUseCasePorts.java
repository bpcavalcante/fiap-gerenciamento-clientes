package com.fiap.gerenciamento_clientes.application.ports;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;

public interface AtualizarClienteUseCasePorts {
    ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO);
}
