package br.com.nonna_ai.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
public class ProdutoPedidoDto {
    @NotBlank(message = "ID do produto é obrigatório")
    private String idProduto;
    @NotNull(message = "O preço é obrigatório")
    private BigDecimal preco;
    public String getIdProduto() { return idProduto; }
    public void setIdProduto(String idProduto) { this.idProduto = idProduto; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}