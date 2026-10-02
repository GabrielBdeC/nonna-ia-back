package br.com.nonna_ai.mapper;

import br.com.nonna_ai.dto.ConfiguracoesDto;
import br.com.nonna_ai.dto.ConfiguracoesResponseDto;
import br.com.nonna_ai.entity.Configuracoes;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracoesMapper {
    public Configuracoes toEntity(ConfiguracoesDto dto) {
        Configuracoes e = new Configuracoes();
        e.setHorarioFuncionamento(dto.getHorarioFuncionamento());
        return e;
    }

    public ConfiguracoesResponseDto toResponseDto(Configuracoes e) {
        ConfiguracoesResponseDto dto = new ConfiguracoesResponseDto();
        dto.setId(e.getId());
        dto.setHorarioFuncionamento(e.getHorarioFuncionamento());
        return dto;
    }
}
