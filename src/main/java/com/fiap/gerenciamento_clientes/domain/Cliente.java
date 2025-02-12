package com.fiap.gerenciamento_clientes.domain;

import com.fiap.gerenciamento_clientes.domain.ports.dto.ClienteDatabaseDTO;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public ClienteDatabaseDTO toDTO() {
        return ClienteDatabaseDTO.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }

}
