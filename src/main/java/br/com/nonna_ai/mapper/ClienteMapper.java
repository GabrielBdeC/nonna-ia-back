package br.com.nonna_ai.mapper;

import br.com.nonna_ai.dto.ClienteDto;
import br.com.nonna_ai.dto.ClienteResponseDto;
import br.com.nonna_ai.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteDto dto) {
        Cliente e = new Cliente();
        e.setCpf(dto.getCpf());
        e.setNome(dto.getNome());
        e.setSobrenome(dto.getSobrenome());
        e.setEmail(dto.getEmail());
        e.setSenha(dto.getSenha());
        return e;
    }

    public ClienteResponseDto toResponseDto(Cliente e) {
        ClienteResponseDto dto = new ClienteResponseDto();
        dto.setId(e.getId());
        dto.setCpf(e.getCpf());
        dto.setNome(e.getNome());
        dto.setSobrenome(e.getSobrenome());
        dto.setEmail(e.getEmail());
        // Sem senha vazada
        return dto;
    }
}
