package br.com.nonna_ai.entity;
import java.math.BigDecimal;
public class ProdutoPedido {
    private String id;
    private String idPedido;
    private String idProduto;
    private BigDecimal preco;
    // getters e setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getIdPedido() { return idPedido; }
    public void setIdPedido(String idPedido) { this.idPedido = idPedido; }
    public String getIdProduto() { return idProduto; }
    public void setIdProduto(String idProduto) { this.idProduto = idProduto; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}