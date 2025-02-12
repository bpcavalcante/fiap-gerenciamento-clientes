package com.fiap.gerenciamento_clientes.application.ports.dto;

import com.fiap.gerenciamento_clientes.application.controller.dto.output.ClienteOutput;
import com.fiap.gerenciamento_clientes.domain.Cliente;
import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public ClienteOutput toOutput() {
        return ClienteOutput.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }

    public Cliente toDomain() {
        return Cliente.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }

    public ClienteDatabaseDTO toDatabaseDTO() {
        return ClienteDatabaseDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }

}
