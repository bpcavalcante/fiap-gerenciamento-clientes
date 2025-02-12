package com.fiap.gerenciamento_clientes.domain.usecase;

import com.fiap.gerenciamento_clientes.application.ports.BuscarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarClienteUseCase implements BuscarClienteUseCasePorts {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public ClienteDTO buscarCliente(Long id) {
        Cliente cliente = clienteRepositoryPort.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado"));
        return cliente.toDTO().toDTO();
    }
}
