package br.com.nonna_ai.repository;
import br.com.nonna_ai.entity.Pedido;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository {
    private final JdbcTemplate jdbc;
    public PedidoRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    
    public void salvarItem(br.com.nonna_ai.entity.ProdutoPedido item) {
        jdbc.update("INSERT INTO produto_pedido (id, id_pedido, id_produto, preco) VALUES (?, ?, ?, ?)",
            item.getId(), item.getIdPedido(), item.getIdProduto(), item.getPreco());
    }
    
    public java.util.List<br.com.nonna_ai.entity.ProdutoPedido> buscarItensPorPedido(String idPedido) {
        return jdbc.query("SELECT * FROM produto_pedido WHERE id_pedido = ?", 
            new BeanPropertyRowMapper<>(br.com.nonna_ai.entity.ProdutoPedido.class), idPedido);
    }
    
    public void salvar(Pedido p) {
        jdbc.update("INSERT INTO pedido (id, id_cliente, preco_total, tipo_entrega, endereco, forma_pagamento, horario_criacao, horario_saida, horario_finalizacao, telefone, status, motivo_cancelamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            p.getId(), p.getIdCliente(), p.getPrecoTotal(), p.getTipoEntrega(), p.getEndereco(), p.getFormaPagamento(), p.getHorarioCriacao(), p.getHorarioSaida(), p.getHorarioFinalizacao(), p.getTelefone(), p.getStatus(), p.getMotivoCancelamento());
    }

    public List<Pedido> buscarNaoConcluidos(int limit, int offset) {
        // Ordenado do mais atrasado e com status mais antigo
        return jdbc.query("SELECT * FROM pedido WHERE status != 'CONCLUIDO' AND status != 'CANCELADO' ORDER BY horario_criacao ASC LIMIT ? OFFSET ?", new BeanPropertyRowMapper<>(Pedido.class), limit, offset);
    }

    public long contarNaoConcluidos() {
        Long c = jdbc.queryForObject("SELECT COUNT(*) FROM pedido WHERE status != 'CONCLUIDO' AND status != 'CANCELADO'", Long.class);
        return c != null ? c : 0;
    }

    public Optional<Pedido> buscarPorId(String id) {
        List<Pedido> list = jdbc.query("SELECT * FROM pedido WHERE id = ?", new BeanPropertyRowMapper<>(Pedido.class), id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public void atualizar(Pedido p) {
        jdbc.update("UPDATE pedido SET status=?, horario_saida=?, horario_finalizacao=?, motivo_cancelamento=? WHERE id=?",
            p.getStatus(), p.getHorarioSaida(), p.getHorarioFinalizacao(), p.getMotivoCancelamento(), p.getId());
    }
}