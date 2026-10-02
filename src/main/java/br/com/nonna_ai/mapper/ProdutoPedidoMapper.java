package br.com.nonna_ai.mapper;
import br.com.nonna_ai.dto.ProdutoPedidoDto;
import br.com.nonna_ai.dto.ProdutoPedidoResponseDto;
import br.com.nonna_ai.entity.ProdutoPedido;
import org.springframework.stereotype.Component;
@Component
public class ProdutoPedidoMapper {
    public ProdutoPedido toEntity(ProdutoPedidoDto dto) {
        ProdutoPedido e = new ProdutoPedido();
        e.setIdProduto(dto.getIdProduto());
        e.setPreco(dto.getPreco());
        return e;
    }
    public ProdutoPedidoResponseDto toResponseDto(ProdutoPedido e) {
        ProdutoPedidoResponseDto dto = new ProdutoPedidoResponseDto();
        dto.setId(e.getId());
        dto.setIdProduto(e.getIdProduto());
        dto.setPreco(e.getPreco());
        return dto;
    
}
}
