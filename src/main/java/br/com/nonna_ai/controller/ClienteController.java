package br.com.nonna_ai.controller;
import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Cliente;
import br.com.nonna_ai.dto.ClienteResponseDto;
import java.util.stream.Collectors;
import br.com.nonna_ai.dto.ClienteDto;
import br.com.nonna_ai.mapper.ClienteMapper;
import jakarta.validation.Valid;

import br.com.nonna_ai.service.ClienteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;
    private final ClienteMapper mapper;
    public ClienteController(ClienteService service, ClienteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public PageDto<ClienteResponseDto> listar(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        PageDto<Cliente> pageDto = service.listar(page, size);
        return new PageDto<>(pageDto.getContent().stream().map(mapper::toResponseDto).collect(Collectors.toList()), pageDto.getPage(), pageDto.getSize(), pageDto.getTotalElements());
    }

    @GetMapping("/{id}")
    public ClienteResponseDto buscarPorId(@PathVariable String id) { return mapper.toResponseDto(service.buscarPorId(id)); }

    @PutMapping("/{id}")
    public ClienteResponseDto atualizar(@PathVariable String id, @RequestBody @Valid ClienteDto dto) { return mapper.toResponseDto(service.atualizar(id, mapper.toEntity(dto))); }
}
