package com.fiap.gerenciamento_clientes.config;

import com.fiap.gerenciamento_clientes.application.ports.AtualizarClienteUseCasePorts;
import com.fiap.gerenciamento_clientes.domain.ports.ClienteRepositoryPort;
import com.fiap.gerenciamento_clientes.domain.usecase.AtualizarClienteUseCase;
import com.fiap.gerenciamento_clientes.domain.usecase.BuscarClienteUseCase;
import com.fiap.gerenciamento_clientes.domain.usecase.CadastrarClienteUseCase;
import com.fiap.gerenciamento_clientes.domain.usecase.DeleteClienteUseCase;
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
    public BuscarClienteUseCase buscarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort){
        return new BuscarClienteUseCase(clienteRepositoryPort);
    }

    @Bean
    public AtualizarClienteUseCase atualizarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort){
        return new AtualizarClienteUseCase(clienteRepositoryPort);
    }

    @Bean
    public DeleteClienteUseCase deleteClienteUseCase(ClienteRepositoryPort clienteRepositoryPort){
        return new DeleteClienteUseCase(clienteRepositoryPort);
    }

    @Bean
    public ClienteSqlRepositoryImpl clienteSqlRepository(ClienteJpaRepository clienteJpaRepository) {
        return new ClienteSqlRepositoryImpl(clienteJpaRepository);
    }



}
