package br.com.nonna_ai.service;
import br.com.nonna_ai.entity.Reserva;
import br.com.nonna_ai.exception.RecursoNaoEncontradoException;
import br.com.nonna_ai.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class ReservaService {
    private final ReservaRepository repository;
    public ReservaService(ReservaRepository repository) { this.repository = repository; }

    @Transactional
    public Reserva criar(Reserva r) {
        r.setId(UUID.randomUUID().toString());
        repository.salvar(r);
        return r;
    }

    public Reserva buscarPorId(String id) {
        return repository.buscarPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException("RESERVA NÃO ENCONTRADA"));
    }

    @Transactional
    public Reserva cancelar(String id, String motivo) {
        Reserva r = buscarPorId(id);
        r.setMotivoCancelamento(motivo);
        repository.atualizar(r);
        return r;
    }
}