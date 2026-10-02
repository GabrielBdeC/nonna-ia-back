package br.com.nonna_ai.service;
import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Produto;
import br.com.nonna_ai.exception.RecursoNaoEncontradoException;
import br.com.nonna_ai.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;
    public ProdutoService(ProdutoRepository repository) { this.repository = repository; }

    @Transactional
    public Produto criar(Produto p) {
        p.setId(UUID.randomUUID().toString());
        repository.salvar(p);
        return p;
    }

    public PageDto<Produto> listar(int page, int size) {
        if (size > 100) size = 100;
        if (size < 1) size = 30;
        List<Produto> content = repository.buscarTodos(size, page * size);
        return new PageDto<>(content, page, size, repository.contarTodos());
    }

    public Produto buscarPorId(String id) {
        return repository.buscarPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException("PRODUTO NÃO ENCONTRADO"));
    }

    @Transactional
    public Produto atualizar(String id, Produto p) {
        Produto existente = buscarPorId(id);
        existente.setNome(p.getNome());
        existente.setDescricao(p.getDescricao());
        existente.setPreco(p.getPreco());
        existente.setIdCategoria(p.getIdCategoria());
        existente.setImagem(p.getImagem());
        repository.atualizar(existente);
        return existente;
    }

    @Transactional
    public void remover(String id) {
        buscarPorId(id);
        repository.deletar(id);
    }
}