package com.fiap.gerenciamento_clientes.infraestructure.implementations;

import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import com.fiap.gerenciamento_clientes.infraestructure.ClienteJpaRepository;
import com.fiap.gerenciamento_clientes.infraestructure.entities.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

@RequiredArgsConstructor
public class ClienteSqlRepositoryImpl implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;


    @Override
    public ClienteDatabaseDTO save(ClienteDatabaseDTO clienteDatabaseDTO) {
        try {
            ClienteEntity entity = ClienteEntity.builder()
                    .nome(clienteDatabaseDTO.getNome())
                    .email(clienteDatabaseDTO.getEmail())
                    .endereco(clienteDatabaseDTO.getEndereco())
                    .telefone(clienteDatabaseDTO.getTelefone())
                    .build();

            return clienteJpaRepository.save(entity).toDatabaseDTO();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao inserir cliente", e);
        }
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id).map(ClienteEntity::toDomain);
    }

    @Override
    public ClienteDatabaseDTO update(Long id, ClienteDatabaseDTO clienteDatabaseDTO) {
        try {
            Optional<ClienteEntity> optionalEntity = clienteJpaRepository.findById(id);
            if (optionalEntity.isEmpty()) {
                throw new RuntimeException("Cliente com ID " + id + " não encontrado.");
            }

            ClienteEntity entity = optionalEntity.get();
            entity.setNome(clienteDatabaseDTO.getNome());
            entity.setEmail(clienteDatabaseDTO.getEmail());
            entity.setEndereco(clienteDatabaseDTO.getEndereco());
            entity.setTelefone(clienteDatabaseDTO.getTelefone());

            return clienteJpaRepository.save(entity).toDatabaseDTO();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao atualizar cliente com ID " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            if (!clienteJpaRepository.existsById(id)) {
                throw new RuntimeException("Cliente com ID " + id + " não encontrado.");
            }
            clienteJpaRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao deletar cliente com ID " + id, e);
        }
    }
}
