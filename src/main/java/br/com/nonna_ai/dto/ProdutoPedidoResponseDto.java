package br.com.nonna_ai.dto;
import java.math.BigDecimal;
public class ProdutoPedidoResponseDto {
    private String id;
    private String idProduto;
    private BigDecimal preco;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getIdProduto() { return idProduto; }
    public void setIdProduto(String idProduto) { this.idProduto = idProduto; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}