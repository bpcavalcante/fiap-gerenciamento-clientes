package com.fiap.gerenciamento_clientes.application;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;

public interface BuscarClienteUseCasePorts {
    ClienteDTO buscarCliente(Long id);
}
