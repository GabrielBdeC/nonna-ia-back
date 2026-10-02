package br.com.nonna_ai.service;

import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Categoria;
import br.com.nonna_ai.exception.RecursoNaoEncontradoException;
import br.com.nonna_ai.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    public CategoriaService(CategoriaRepository repository) { this.repository = repository; }

    @Transactional
    public Categoria criar(Categoria categoria) {
        categoria.setId(UUID.randomUUID().toString());
        repository.salvar(categoria);
        return categoria;
    }

    public PageDto<Categoria> listar(int page, int size) {
        if (size > 100) size = 100;
        if (size < 1) size = 30;
        int offset = page * size;
        List<Categoria> content = repository.buscarTodas(size, offset);
        long total = repository.contarTodas();
        return new PageDto<>(content, page, size, total);
    }

    public Categoria buscarPorId(String id) {
        return repository.buscarPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException("CATEGORIA NÃO ENCONTRADA"));
    }

    @Transactional
    public Categoria atualizar(String id, Categoria dados) {
        Categoria c = buscarPorId(id);
        c.setNome(dados.getNome());
        repository.atualizar(c);
        return c;
    }

    @Transactional
    public void remover(String id) {
        Categoria c = buscarPorId(id);
        repository.deletar(c.getId());
    }
}