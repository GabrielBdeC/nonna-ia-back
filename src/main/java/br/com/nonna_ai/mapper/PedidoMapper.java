package br.com.nonna_ai.mapper;

import br.com.nonna_ai.dto.PedidoDto;
import br.com.nonna_ai.dto.PedidoResponseDto;
import br.com.nonna_ai.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    public Pedido toEntity(PedidoDto dto) {
        Pedido e = new Pedido();
        e.setIdCliente(dto.getIdCliente());
        e.setPrecoTotal(dto.getPrecoTotal());
        e.setTipoEntrega(dto.getTipoEntrega());
        e.setEndereco(dto.getEndereco());
        e.setFormaPagamento(dto.getFormaPagamento());
        e.setTelefone(dto.getTelefone());
        return e;
    }

    public PedidoResponseDto toResponseDto(Pedido e) {
        PedidoResponseDto dto = new PedidoResponseDto();
        dto.setId(e.getId());
        dto.setIdCliente(e.getIdCliente());
        dto.setPrecoTotal(e.getPrecoTotal());
        dto.setTipoEntrega(e.getTipoEntrega());
        dto.setEndereco(e.getEndereco());
        dto.setFormaPagamento(e.getFormaPagamento());
        dto.setHorarioCriacao(e.getHorarioCriacao());
        dto.setHorarioSaida(e.getHorarioSaida());
        dto.setHorarioFinalizacao(e.getHorarioFinalizacao());
        dto.setTelefone(e.getTelefone());
        dto.setStatus(e.getStatus());
        dto.setMotivoCancelamento(e.getMotivoCancelamento());
        return dto;
    }
}
