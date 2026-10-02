package br.com.nonna_ai.mapper;

import br.com.nonna_ai.dto.ProdutoDto;
import br.com.nonna_ai.dto.ProdutoResponseDto;
import br.com.nonna_ai.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public Produto toEntity(ProdutoDto dto) {
        Produto e = new Produto();
        e.setNome(dto.getNome());
        e.setDescricao(dto.getDescricao());
        e.setPreco(dto.getPreco());
        e.setIdCategoria(dto.getIdCategoria());
        e.setImagem(dto.getImagem());
        return e;
    }

    public ProdutoResponseDto toResponseDto(Produto e) {
        ProdutoResponseDto dto = new ProdutoResponseDto();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setDescricao(e.getDescricao());
        dto.setPreco(e.getPreco());
        dto.setIdCategoria(e.getIdCategoria());
        dto.setImagem(e.getImagem());
        return dto;
    }
}
