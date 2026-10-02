package br.com.nonna_ai.controller;
import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Produto;
import br.com.nonna_ai.dto.ProdutoResponseDto;
import java.util.stream.Collectors;
import br.com.nonna_ai.dto.ProdutoDto;
import br.com.nonna_ai.mapper.ProdutoMapper;
import jakarta.validation.Valid;

import br.com.nonna_ai.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;
    private final ProdutoMapper mapper;
    public ProdutoController(ProdutoService service, ProdutoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDto criar(@RequestBody @Valid ProdutoDto dto) { return mapper.toResponseDto(service.criar(mapper.toEntity(dto))); }

    @GetMapping
    public PageDto<ProdutoResponseDto> listar(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        PageDto<Produto> pageDto = service.listar(page, size);
        return new PageDto<>(pageDto.getContent().stream().map(mapper::toResponseDto).collect(Collectors.toList()), pageDto.getPage(), pageDto.getSize(), pageDto.getTotalElements());
    }

    @GetMapping("/{id}")
    public ProdutoResponseDto buscarPorId(@PathVariable String id) { return mapper.toResponseDto(service.buscarPorId(id)); }

    @PutMapping("/{id}")
    public ProdutoResponseDto atualizar(@PathVariable String id, @RequestBody @Valid ProdutoDto dto) { return mapper.toResponseDto(service.atualizar(id, mapper.toEntity(dto))); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String id) { service.remover(id); }
}
