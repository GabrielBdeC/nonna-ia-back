package br.com.nonna_ai.controller;

import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Categoria;
import br.com.nonna_ai.dto.CategoriaResponseDto;
import java.util.stream.Collectors;
import br.com.nonna_ai.dto.CategoriaDto;
import br.com.nonna_ai.mapper.CategoriaMapper;
import jakarta.validation.Valid;

import br.com.nonna_ai.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;
    private final CategoriaMapper mapper;
    public CategoriaController(CategoriaService service, CategoriaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDto criar(@RequestBody @Valid CategoriaDto dto) { // Adicionar DTO depois
        return mapper.toResponseDto(service.criar(mapper.toEntity(dto)));
    }

    @GetMapping
    public PageDto<CategoriaResponseDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        PageDto<Categoria> pageDto = service.listar(page, size);
        return new PageDto<>(pageDto.getContent().stream().map(mapper::toResponseDto).collect(Collectors.toList()), pageDto.getPage(), pageDto.getSize(), pageDto.getTotalElements());
    }

    @PutMapping("/{id}")
    public CategoriaResponseDto atualizar(@PathVariable String id, @RequestBody @Valid CategoriaDto dto) {
        return mapper.toResponseDto(service.atualizar(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String id) {
        service.remover(id);
    }
}
