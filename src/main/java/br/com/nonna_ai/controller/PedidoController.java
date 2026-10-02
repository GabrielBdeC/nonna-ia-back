package br.com.nonna_ai.controller;
import br.com.nonna_ai.dto.PageDto;
import br.com.nonna_ai.entity.Pedido;
import br.com.nonna_ai.dto.PedidoResponseDto;
import br.com.nonna_ai.entity.ProdutoPedido;
import br.com.nonna_ai.mapper.ProdutoPedidoMapper;
import java.util.List;
import java.util.stream.Collectors;
import br.com.nonna_ai.dto.PedidoDto;
import br.com.nonna_ai.mapper.PedidoMapper;
import jakarta.validation.Valid;

import br.com.nonna_ai.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService service;
    private final PedidoMapper mapper;
    private final ProdutoPedidoMapper itemMapper;
    public PedidoController(PedidoService service, PedidoMapper mapper, ProdutoPedidoMapper itemMapper) {
        this.service = service;
        this.mapper = mapper;
        this.itemMapper = itemMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDto criar(@RequestBody @Valid PedidoDto dto) { 
        List<ProdutoPedido> itens = dto.getItens() != null ? dto.getItens().stream().map(itemMapper::toEntity).collect(Collectors.toList()) : null;
        Pedido p = service.criar(mapper.toEntity(dto), itens);
        PedidoResponseDto resp = mapper.toResponseDto(p);
        if (itens != null) {
            resp.setItens(itens.stream().map(itemMapper::toResponseDto).collect(Collectors.toList()));
        }
        return resp; }

    @GetMapping
    public PageDto<PedidoResponseDto> listar(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        PageDto<Pedido> pageDto = service.listarNaoConcluidos(page, size);
        return new PageDto<>(pageDto.getContent().stream().map(mapper::toResponseDto).collect(Collectors.toList()), pageDto.getPage(), pageDto.getSize(), pageDto.getTotalElements());
    }

    @GetMapping("/{id}")
    public PedidoResponseDto buscarPorId(@PathVariable String id) { 
        Pedido p = service.buscarPorId(id);
        PedidoResponseDto resp = mapper.toResponseDto(p);
        List<ProdutoPedido> itens = service.buscarItens(id);
        resp.setItens(itens.stream().map(itemMapper::toResponseDto).collect(Collectors.toList()));
        return resp; }

    @PutMapping("/{id}")
    public PedidoResponseDto atualizar(@PathVariable String id, @RequestBody @Valid PedidoDto dto) { return mapper.toResponseDto(service.atualizar(id, mapper.toEntity(dto))); }

    @PostMapping("/{id}/cancelar")
    public PedidoResponseDto cancelar(@PathVariable String id, @RequestBody Map<String, String> body) { 
        return mapper.toResponseDto(service.cancelar(id, body.get("motivo"))); 
    }
}
