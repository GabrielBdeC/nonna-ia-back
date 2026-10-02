package br.com.nonna_ai.controller;

import br.com.nonna_ai.entity.Configuracoes;
import br.com.nonna_ai.dto.ConfiguracoesResponseDto;
import java.util.stream.Collectors;
import br.com.nonna_ai.dto.ConfiguracoesDto;
import br.com.nonna_ai.mapper.ConfiguracoesMapper;
import br.com.nonna_ai.service.ConfiguracoesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuracoes")
public class ConfiguracoesController {
    private final ConfiguracoesService service;
    private final ConfiguracoesMapper mapper;
    
    public ConfiguracoesController(ConfiguracoesService service, ConfiguracoesMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConfiguracoesResponseDto criar(@RequestBody @Valid ConfiguracoesDto dto) { 
        return mapper.toResponseDto(service.criar(mapper.toEntity(dto))); 
    }

    @GetMapping
    public ConfiguracoesResponseDto buscar() {
        return mapper.toResponseDto(service.buscar());
    }
}
