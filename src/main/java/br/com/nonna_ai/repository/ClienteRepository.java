package br.com.nonna_ai.repository;
import br.com.nonna_ai.entity.Cliente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {
    private final JdbcTemplate jdbc;
    public ClienteRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Cliente> buscarTodos(int limit, int offset) {
        return jdbc.query("SELECT * FROM cliente LIMIT ? OFFSET ?", new BeanPropertyRowMapper<>(Cliente.class), limit, offset);
    }

    public long contarTodos() {
        Long c = jdbc.queryForObject("SELECT COUNT(*) FROM cliente", Long.class);
        return c != null ? c : 0;
    }

    public Optional<Cliente> buscarPorId(String id) {
        List<Cliente> list = jdbc.query("SELECT * FROM cliente WHERE id = ?", new BeanPropertyRowMapper<>(Cliente.class), id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public void atualizar(Cliente c) {
        jdbc.update("UPDATE cliente SET nome=?, sobrenome=?, email=?, senha=?, cpf=? WHERE id=?",
            c.getNome(), c.getSobrenome(), c.getEmail(), c.getSenha(), c.getCpf(), c.getId());
    }
}