package br.com.nonna_ai.service;
import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Cliente;
import br.com.nonna_ai.exception.RecursoNaoEncontradoException;
import br.com.nonna_ai.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;
    public ClienteService(ClienteRepository repository) { this.repository = repository; }

    public PageDto<Cliente> listar(int page, int size) {
        if (size > 100) size = 100;
        if (size < 1) size = 30;
        return new PageDto<>(repository.buscarTodos(size, page * size), page, size, repository.contarTodos());
    }

    public Cliente buscarPorId(String id) {
        return repository.buscarPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException("CLIENTE NÃO ENCONTRADO"));
    }

    @Transactional
    public Cliente atualizar(String id, Cliente p) {
        Cliente existente = buscarPorId(id);
        existente.setNome(p.getNome());
        existente.setSobrenome(p.getSobrenome());
        existente.setEmail(p.getEmail());
        existente.setSenha(p.getSenha());
        existente.setCpf(p.getCpf());
        repository.atualizar(existente);
        return existente;
    }
}