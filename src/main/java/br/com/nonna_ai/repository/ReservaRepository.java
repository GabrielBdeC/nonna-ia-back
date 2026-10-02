package br.com.nonna_ai.repository;
import br.com.nonna_ai.entity.Reserva;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository {
    private final JdbcTemplate jdbc;
    public ReservaRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public void salvar(Reserva r) {
        jdbc.update("INSERT INTO reserva (id, id_cliente, horario, quantidade_pessoas, tipo_evento, motivo_cancelamento) VALUES (?, ?, ?, ?, ?, ?)",
            r.getId(), r.getIdCliente(), r.getHorario(), r.getQuantidadePessoas(), r.getTipoEvento(), r.getMotivoCancelamento());
    }

    public Optional<Reserva> buscarPorId(String id) {
        List<Reserva> list = jdbc.query("SELECT * FROM reserva WHERE id = ?", new BeanPropertyRowMapper<>(Reserva.class), id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public void atualizar(Reserva r) {
        jdbc.update("UPDATE reserva SET motivo_cancelamento=? WHERE id=?", r.getMotivoCancelamento(), r.getId());
    }
}