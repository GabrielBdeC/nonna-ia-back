package br.com.nonna_ai.service;
import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Pedido;
import br.com.nonna_ai.entity.ProdutoPedido;
import br.com.nonna_ai.exception.RecursoNaoEncontradoException;
import br.com.nonna_ai.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {
    private final PedidoRepository repository;
    public PedidoService(PedidoRepository repository) { this.repository = repository; }

    @Transactional
    public Pedido criar(Pedido p, List<ProdutoPedido> itens) {
        p.setId(UUID.randomUUID().toString());
        p.setHorarioCriacao(LocalDateTime.now());
        p.setStatus("CRIADO");
        repository.salvar(p);
        
        if (itens != null) {
            for (ProdutoPedido item : itens) {
                item.setId(UUID.randomUUID().toString());
                item.setIdPedido(p.getId());
                repository.salvarItem(item);
            }
        }
        return p;
    }

    public PageDto<Pedido> listarNaoConcluidos(int page, int size) {
        if (size > 100) size = 100;
        if (size < 1) size = 30;
        return new PageDto<>(repository.buscarNaoConcluidos(size, page * size), page, size, repository.contarNaoConcluidos());
    }

    public Pedido buscarPorId(String id) {
        return repository.buscarPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException("PEDIDO NÃO ENCONTRADO"));
    }
    
    public List<ProdutoPedido> buscarItens(String idPedido) {
        return repository.buscarItensPorPedido(idPedido);
    }

    @Transactional
    public Pedido atualizar(String id, Pedido p) {
        Pedido existente = buscarPorId(id);
        if (p.getStatus() != null) existente.setStatus(p.getStatus());
        if (p.getHorarioSaida() != null) existente.setHorarioSaida(p.getHorarioSaida());
        if (p.getHorarioFinalizacao() != null) existente.setHorarioFinalizacao(p.getHorarioFinalizacao());
        repository.atualizar(existente);
        return existente;
    }

    @Transactional
    public Pedido cancelar(String id, String motivo) {
        Pedido existente = buscarPorId(id);
        existente.setStatus("CANCELADO");
        existente.setMotivoCancelamento(motivo);
        repository.atualizar(existente);
        return existente;
    }
}
