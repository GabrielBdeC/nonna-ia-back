package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Configuracoes;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConfiguracoesRepository {
    private final JdbcTemplate jdbc;
    
    public ConfiguracoesRepository(JdbcTemplate jdbc) { 
        this.jdbc = jdbc; 
    }

    public void salvar(Configuracoes c) {
        jdbc.update("INSERT INTO configuracoes (id, horario_funcionamento) VALUES (?, ?)", c.getId(), c.getHorarioFuncionamento());
    }

    public Optional<Configuracoes> buscarAtual() {
        List<Configuracoes> list = jdbc.query("SELECT * FROM configuracoes LIMIT 1", new BeanPropertyRowMapper<>(Configuracoes.class));
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
