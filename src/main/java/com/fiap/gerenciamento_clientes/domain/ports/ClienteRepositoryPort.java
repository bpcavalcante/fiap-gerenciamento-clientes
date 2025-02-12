package com.fiap.gerenciamento_clientes.domain.ports;

import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;

public interface ClienteRepositoryPort {
    ClienteDatabaseDTO save(ClienteDatabaseDTO clienteDatabaseDTO);
}
