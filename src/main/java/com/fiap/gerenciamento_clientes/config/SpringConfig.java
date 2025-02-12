package com.fiap.gerenciamento_clientes.config;

import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.usecase.CadastrarClienteUseCase;
import com.fiap.gerenciamento_clientes.infraestructure.ClienteJpaRepository;
import com.fiap.gerenciamento_clientes.infraestructure.implementations.ClienteSqlRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    public CadastrarClienteUseCase cadastrarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new CadastrarClienteUseCase(clienteRepositoryPort);
    }

    @Bean
    public ClienteSqlRepositoryImpl clienteSqlRepository(ClienteJpaRepository clienteJpaRepository) {
        return new ClienteSqlRepositoryImpl(clienteJpaRepository);
    }

}
