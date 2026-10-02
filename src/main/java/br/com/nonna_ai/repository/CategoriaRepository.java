package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Categoria;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository {
    private final JdbcTemplate jdbc;
    public CategoriaRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public void salvar(Categoria c) {
        jdbc.update("INSERT INTO categoria (id, nome) VALUES (?, ?)", c.getId(), c.getNome());
    }

    public List<Categoria> buscarTodas(int limit, int offset) {
        return jdbc.query("SELECT * FROM categoria LIMIT ? OFFSET ?", new BeanPropertyRowMapper<>(Categoria.class), limit, offset);
    }

    public long contarTodas() {
        Long count = jdbc.queryForObject("SELECT COUNT(*) FROM categoria", Long.class);
        return count != null ? count : 0;
    }

    public Optional<Categoria> buscarPorId(String id) {
        List<Categoria> list = jdbc.query("SELECT * FROM categoria WHERE id = ?", new BeanPropertyRowMapper<>(Categoria.class), id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public void atualizar(Categoria c) {
        jdbc.update("UPDATE categoria SET nome = ? WHERE id = ?", c.getNome(), c.getId());
    }

    public void deletar(String id) {
        jdbc.update("DELETE FROM categoria WHERE id = ?", id);
    }
}