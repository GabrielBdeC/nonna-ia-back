package br.com.nonna_ai.service;

import br.com.nonna_ai.entity.Configuracoes;
import br.com.nonna_ai.exception.RecursoNaoEncontradoException;
import br.com.nonna_ai.repository.ConfiguracoesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ConfiguracoesService {
    private final ConfiguracoesRepository repository;
    
    public ConfiguracoesService(ConfiguracoesRepository repository) { 
        this.repository = repository; 
    }

    @Transactional
    public Configuracoes criar(Configuracoes c) {
        c.setId(UUID.randomUUID().toString());
        repository.salvar(c);
        return c;
    }

    public Configuracoes buscar() {
        return repository.buscarAtual().orElseThrow(() -> new RecursoNaoEncontradoException("CONFIGURAÇÕES NÃO ENCONTRADAS"));
    }
}
