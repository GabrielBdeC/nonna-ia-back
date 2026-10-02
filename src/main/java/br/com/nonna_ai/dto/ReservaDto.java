package br.com.nonna_ai.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
public class ReservaDto {
    @NotBlank(message = "ID do cliente é obrigatório")
    private String idCliente;
    @NotNull(message = "O horário é obrigatório")
    private LocalDateTime horario;
    @NotNull(message = "Quantidade de pessoas é obrigatória")
    @Positive(message = "A quantidade deve ser positiva")
    private Integer quantidadePessoas;
    private String tipoEvento;
    
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public LocalDateTime getHorario() { return horario; }
    public void setHorario(LocalDateTime horario) { this.horario = horario; }
    public Integer getQuantidadePessoas() { return quantidadePessoas; }
    public void setQuantidadePessoas(Integer quantidadePessoas) { this.quantidadePessoas = quantidadePessoas; }
    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }
}