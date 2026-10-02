package br.com.nonna_ai.mapper;

import br.com.nonna_ai.dto.CategoriaDto;
import br.com.nonna_ai.dto.CategoriaResponseDto;
import br.com.nonna_ai.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public Categoria toEntity(CategoriaDto dto) {
        Categoria e = new Categoria();
        e.setNome(dto.getNome());
        return e;
    }

    public CategoriaResponseDto toResponseDto(Categoria e) {
        CategoriaResponseDto dto = new CategoriaResponseDto();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        return dto;
    }
}
