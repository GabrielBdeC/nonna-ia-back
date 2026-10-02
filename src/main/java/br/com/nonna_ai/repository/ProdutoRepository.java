package br.com.nonna_ai.repository;
import br.com.nonna_ai.entity.Produto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    private final JdbcTemplate jdbc;
    public ProdutoRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public void salvar(Produto p) {
        jdbc.update("INSERT INTO produto (id, nome, descricao, preco, id_categoria, imagem) VALUES (?, ?, ?, ?, ?, ?)",
            p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getIdCategoria(), p.getImagem());
    }

    public List<Produto> buscarTodos(int limit, int offset) {
        return jdbc.query("SELECT * FROM produto LIMIT ? OFFSET ?", new BeanPropertyRowMapper<>(Produto.class), limit, offset);
    }

    public long contarTodos() {
        Long count = jdbc.queryForObject("SELECT COUNT(*) FROM produto", Long.class);
        return count != null ? count : 0;
    }

    public Optional<Produto> buscarPorId(String id) {
        List<Produto> list = jdbc.query("SELECT * FROM produto WHERE id = ?", new BeanPropertyRowMapper<>(Produto.class), id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public void atualizar(Produto p) {
        jdbc.update("UPDATE produto SET nome=?, descricao=?, preco=?, id_categoria=?, imagem=? WHERE id=?",
            p.getNome(), p.getDescricao(), p.getPreco(), p.getIdCategoria(), p.getImagem(), p.getId());
    }

    public void deletar(String id) {
        jdbc.update("DELETE FROM produto WHERE id = ?", id);
    }
}