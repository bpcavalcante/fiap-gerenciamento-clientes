package com.fiap.gerenciamento_clientes.domain.usecase;

import com.fiap.gerenciamento_clientes.application.ports.CadastrarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarClienteUseCase implements CadastrarClienteUseCasePorts {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public ClienteDTO cadastrar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteDTO.toDomain();
        ClienteDatabaseDTO clienteDatabaseDTO = clienteRepositoryPort.save(cliente.toDTO());
        return clienteDatabaseDTO.toDTO();
    }
}
