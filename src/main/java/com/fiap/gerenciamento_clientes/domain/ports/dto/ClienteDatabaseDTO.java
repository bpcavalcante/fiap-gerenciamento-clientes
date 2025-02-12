package com.fiap.gerenciamento_clientes.domain.ports.dto;

import com.fiap.gerenciamento_clientes.application.ports.dto.ClienteDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteDatabaseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public ClienteDTO toDTO() {
        return ClienteDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }

}
