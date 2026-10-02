package br.com.nonna_ai.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
public class PedidoDto {
    @NotBlank(message = "ID do cliente é obrigatório")
    private String idCliente;
    @NotNull(message = "O preço total é obrigatório")
    private BigDecimal precoTotal;
    @NotBlank(message = "Tipo de entrega é obrigatório")
    private String tipoEntrega;
    private String endereco;
    @NotBlank(message = "Forma de pagamento é obrigatória")
    private String formaPagamento;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @jakarta.validation.Valid
    private java.util.List<ProdutoPedidoDto> itens;
    
    // getters and setters
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public BigDecimal getPrecoTotal() { return precoTotal; }
    public void setPrecoTotal(BigDecimal precoTotal) { this.precoTotal = precoTotal; }
    public String getTipoEntrega() { return tipoEntrega; }
    public void setTipoEntrega(String tipoEntrega) { this.tipoEntrega = tipoEntrega; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    public java.util.List<ProdutoPedidoDto> getItens() { return itens; }
    public void setItens(java.util.List<ProdutoPedidoDto> itens) { this.itens = itens; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}